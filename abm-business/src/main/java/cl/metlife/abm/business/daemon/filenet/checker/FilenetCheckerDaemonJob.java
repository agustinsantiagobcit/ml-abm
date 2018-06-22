package cl.metlife.abm.business.daemon.filenet.checker;

import cl.metlife.abm.business.managers.*;
import cl.metlife.abm.business.managers.filenet.FilenetManager;
import cl.metlife.abm.business.managers.filenet.exception.EncodeException;
import cl.metlife.abm.business.managers.localfolder.LocalFolderManager;
import cl.metlife.abm.business.managers.preingreso.PreIngresoManager;
import cl.metlife.abm.business.managers.sftp.SFTPManager;
import cl.metlife.abm.domain.*;
import cl.metlife.abm.domain.Process;
import cl.metlife.abm.persistence.dao.LotStatusDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static cl.metlife.abm.domain.FileType.ALTA_CARGA_INT;
import static cl.metlife.abm.domain.FileType.ALTA_TITULAR_INT;
import static cl.metlife.abm.domain.FileType.BAJA_TITULAR_INT;
import static cl.metlife.abm.domain.FileType.BAJA_CARGA_INT;

/**
 * Created by BluePrints Developer on 23-05-2016.
 */
@Stateless(name = "FilenetCheckerDaemon")
public class FilenetCheckerDaemonJob {

    static final Logger logger = LoggerFactory.getLogger(FilenetCheckerDaemonJob.class);

    @EJB
    LotManager lotManager;

    @EJB
    DetailManager detailManager;

    @EJB
    FilenetManager filenetManager;

    @EJB
    LogbookManager logbookManager;

    @EJB
    LocalFolderManager localFolderManager;

    @EJB
    PreIngresoManager preIngresoManager;

    @EJB
    LotStatusDAO lotStatusDAO;

    @EJB
    ABMConfigurationManager abmConfigurationManager;

    @EJB
    MailSendlManager mailSendlManager;


    public FilenetCheckerDaemonJob() {}

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void startProcess() {
        List<Lot> lots = lotManager.findByLotStatus(LotStatus.LOADED_IN_PIPESPONSOR);

        for (Lot lot : lots) {
            logbookManager.info(lot.getProcess(), Log.LOG_STEP_FILENET, "Se procesará el lote " + lot.getId() + " en filenet. Nombre del archivo: " + lot.getFilename(), null);
            Lot processed = processLot(lot);
            checkErrorsAndSendMail(processed, processed.getProcess());
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    private Lot processLot(Lot lot) {
        List<Detail> detailList = detailManager.findByLotId(lot.getId(), DetailsStatus.CREATED);
        HashMap<String, String> pdfEnFilenet = new HashMap<String, String>();

        int error = 0;
        for (Detail detail : detailList) {
            if(detail.getStatusId().equals(DetailsStatus.CREATED) || detail.getStatusId().equals(DetailsStatus.ERROR)){
                if(lot.getProcess().isConsiderBarcodes()){
                    String barCode = getBarcodeByFiletype(lot.getFileTypeId().intValue(), detail);
                    String pdfPath = barCode + ".pdf";
                    detail.setRutaPdf(pdfPath);

                    try {
                        String filenetCode = "";
                        if(!pdfEnFilenet.containsKey(pdfPath)){
                            String filenetOldCode = filenetManager.searchDocuments(pdfPath, lot.getProcess().getBrokerRut().replace("-", "").replace(".", ""),
                                    abmConfigurationManager.getByKey(ABMConfiguration.FILENET_CREATORTASKID).getValor(), abmConfigurationManager.getByKey(ABMConfiguration.FILENET_APPLICATIONID).getValor());

                            if (filenetOldCode != null){
                                filenetCode = filenetOldCode;
                                updateDetailStatus(detail, filenetCode, DetailsStatus.LOADED_IN_FILENET);
                                logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_INFO, Log.LOG_STEP_FILENET, "El pdf " + pdfPath + " ya había sido subido a Filenet anteriormente. Se utilizará el mismo código [CODE:" + filenetCode +"]", lot, detail, null, null);
                                pdfEnFilenet.put(pdfPath, filenetOldCode);

                                preIngresoManager.registerFilenet(detail, filenetCode);
                                updateDetailStatus(detail, filenetCode, DetailsStatus.FINALIZED);
                            } else {
                                boolean exist = localFolderManager.existPDFFile(lot.getProcess().getBrokerRut().replace(".", ""), pdfPath);

                                if(exist) {
                                    byte[] fileStream = localFolderManager.getFile(lot.getProcess().getBrokerRut().replace(".", ""), pdfPath, SFTPManager.PDF_FOLDER_NAME);
                                    filenetCode = filenetManager.uploadFile(lot, pdfPath, fileStream, detail);
                                    pdfEnFilenet.put(pdfPath, filenetCode);

                                    logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_INFO, Log.LOG_STEP_FILENET, "Se subió correctamente el pdf " + pdfPath + " a filenet. [CODE:" + filenetCode +"]", lot, detail, null, null);

                                    updateDetailStatus(detail, filenetCode, DetailsStatus.LOADED_IN_FILENET);

                                    preIngresoManager.registerFilenet(detail, filenetCode);
                                    updateDetailStatus(detail, filenetCode, DetailsStatus.FINALIZED);
                                    localFolderManager.renameFile(lot.getProcess().getBrokerRut().replace(".", ""), Lot.PROCESSING_SUFFIX + pdfPath, Lot.SUCCESSFUL_SUFFIX + pdfPath, SFTPManager.PDF_FOLDER_NAME);
                                } else{
                                    logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_ERROR, Log.LOG_STEP_FILENET, "No existe el PDF " + pdfPath + " en la carpeta SFTP", lot, detail, null, null);
                                    error++;
                                }
                            }
                        } else {
                            filenetCode = pdfEnFilenet.get(pdfPath);
                            logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_INFO, Log.LOG_STEP_FILENET, "Otro registro del archivo referenciaba al mismo archivo pdf " + pdfPath + ". Se utilizará el código anteriormente obtenido [CODE:" + filenetCode +"]", lot, detail, null, null);
                            updateDetailStatus(detail, filenetCode, DetailsStatus.LOADED_IN_FILENET);

                            preIngresoManager.registerFilenet(detail, filenetCode);
                            updateDetailStatus(detail, filenetCode, DetailsStatus.FINALIZED);
                        }

                    } catch (EncodeException e) {
                        logger.error("Error de enconde", e);
                        logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_ERROR, Log.LOG_STEP_FILENET, "Hubo un error al subir el archivo a Filenet: Encode Exception.", lot, detail, e, null);
                        detail.setStatusId(DetailsStatus.ERROR);
                        detailManager.update(detail);
                        error++;
                    } catch (SQLException e) {
                        logger.error("Error de SQL", e);
                        logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_ERROR, Log.LOG_STEP_FILENET, "Hubo un error subir el archivo a Filenet", lot, detail, e, null);
                        detail.setStatusId(DetailsStatus.ERROR);
                        detailManager.update(detail);
                        error++;
                    }
                }
            }
        }

        if(error == 0){
            lot.setLotStatus(lotStatusDAO.getById(LotStatus.FINALIZED));
            lot.setLotStatusId(LotStatus.FINALIZED);
            lot.setEndDate(new Date());

            lotManager.update(lot);
        } else {
            lot.setLotStatus(lotStatusDAO.getById(LotStatus.LOADED_IN_PIPESPONSOR));
            lot.setLotStatusId(LotStatus.LOADED_IN_PIPESPONSOR);

            lotManager.update(lot);
        }

        return lot;
    }

    private void checkErrorsAndSendMail(Lot processed, Process process) {
        List<Log> logs = logbookManager.findByProcessIdAndStatus(processed.getProcessId(), processed.getId(), Log.LOG_LEVEL_ERROR);

        try{
            if(!logs.isEmpty()){
                mailSendlManager.sendMail(processed, logs, process);
                logbookManager.log(processed.getProcess(), Log.LOG_LEVEL_INFO, Log.LOG_STEP_EJECUCION_GENERAL, "Se envió email con errores al correo: " + processed.getProcess().getErrorMail(), processed, null, null, null);
            } else
                logger.debug("No hay logs de error para ser enviados.");
        } catch (Exception e){
            logbookManager.log(processed.getProcess(), Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al enviar correo con errores", processed, null, e, null);
        }
    }

    private void updateDetailStatus(Detail detail, String filenetCode, Long loadedInFilenet) {
        detail.setStatusId(loadedInFilenet);
        detail.setIdFilenet(filenetCode);
        detailManager.update(detail);
    }

    private String getBarcodeByFiletype(int i, Detail detail) {
        String barCode = null;

        switch (i){
            case ALTA_TITULAR_INT: barCode = detailManager.getColumnValue(ALTA_TITULAR_INT, detail); break;
            case ALTA_CARGA_INT: barCode = detailManager.getColumnValue(ALTA_CARGA_INT, detail); break;
            case BAJA_TITULAR_INT: barCode = detailManager.getColumnValue(BAJA_TITULAR_INT, detail); break;
            case BAJA_CARGA_INT: barCode =detailManager.getColumnValue(BAJA_CARGA_INT, detail); break;
        }

        return barCode;
    }

}
