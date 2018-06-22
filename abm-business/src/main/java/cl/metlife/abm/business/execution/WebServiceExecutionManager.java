package cl.metlife.abm.business.execution;

import cl.metlife.abm.business.execution.exception.FileTypeException;
import cl.metlife.abm.business.managers.*;
import cl.metlife.abm.business.managers.localfolder.LocalFolderManager;
import cl.metlife.abm.business.managers.pipesponsor.PipeSponsorManager;
import cl.metlife.abm.business.managers.pipesponsor.exception.PipeSponsorException;
import cl.metlife.abm.business.managers.preingreso.PreIngresoManager;
import cl.metlife.abm.business.managers.preingreso.exception.PreIngresoException;
import cl.metlife.abm.business.managers.sftp.SFTPManager;
import cl.metlife.abm.business.managers.sftp.exception.SFTPConnectionException;
import cl.metlife.abm.domain.*;
import cl.metlife.abm.domain.Process;
import cl.metlife.abm.persistence.dao.exception.PipeSponsorDBException;
import cl.metlife.abm.ws.domain.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Stateless
public class WebServiceExecutionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceExecutionManager.class);
    private static final DateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH-mm");

    @EJB
    private LogbookManager logbookManager;

    @EJB
    private SFTPManager sftpManager;

    @EJB
    private PreIngresoManager preIngresoManager;

    @EJB
    private PipeSponsorManager pipeSponsorManager;

    @EJB
    private LotManager lotManager;

    @EJB
    private DetailManager detailManager;

    @EJB
    private FileTypeManager fileTypeManager;

    @EJB
    private PipeTablesManager pipeTablesManager;

    @EJB
    private LocalFolderManager localFolderManager;

    @EJB
    private ProcessManager processManager;


    public ProcessResult execute(String brokerRut, String lotNumber, Date date, String movementType, byte[] fileBytes, String user) {
        String rut = brokerRut;
        Process process = processManager.getByMultiBrokerRut(rut);
        ArrayList<String> availableRuts = new ArrayList<String>(processManager.getAllRegisteredRuts());


        if(process == null){
            return new ProcessResult(ProcessResult.STATUS_ERROR,"El rut " + rut + " no corresponde a un Corredor válido. Rut disponibles: "
                    + availableRuts.toString());
        }

        logbookManager.info(process, Log.LOG_STEP_EJECUCION_GENERAL ,"Iniciando Proceso por Servicio Web " +
                (process.getProcessTypeId().equals(ProcessType.PROCESS_TYPE_NORMAL) ? "Corredor ": " Multicorredor")
                + " " + process.getBrokerName() + " (" + process.getBrokerRut() + ")" , null);

        try{
            Long fileTypeId = getFileTypeIdByWsId(movementType);
            Long pipeSponsorId = getPipeSponsorId(process, fileTypeId);

            String fileName = "ws_lot_" + format.format(new Date()) + ".csv";
            Lot persistedLot = lotManager.create(new Lot(new Date(), fileTypeId, process.getId(), fileName, lotNumber, LotStatus.FINALIZED));

            byte[] fileBytesFormatted = fixFile(new String(fileBytes), persistedLot.getId());

            // Uploading file into Pipe.
            boolean pipeResponse = callToPipeSponsor(brokerRut, process, fileName, pipeSponsorId, persistedLot, fileBytesFormatted);

            if(!pipeResponse)
                return new ProcessResult(ProcessResult.STATUS_ERROR,"Hubo un error al cargar el archivo a PipeSponsor");

            // Processing data after be uploaded in Pipe.
            processRowsAfterPipeLoad(process, brokerRut, fileTypeId, persistedLot, user);

            logbookManager.info(process, Log.LOG_STEP_EJECUCION_GENERAL ,"Proceso Servicio Web Finalizado. " +
                    (process.getProcessTypeId().equals(ProcessType.PROCESS_TYPE_NORMAL) ? "Corredor ": " Multicorredor")
                    + " " + process.getBrokerName() + " (" + process.getBrokerRut() + ")" , null);

            return new ProcessResult(ProcessResult.STATUS_SUCCESS,"Se procesó correctamente el archivo. Proceso Servicio Web Finalizado.");

        } catch (PipeSponsorException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PIPESPONSOR, e.getMessage(), e, null);
            LOGGER.error(e.getMessage(), e);
            return new ProcessResult(ProcessResult.STATUS_ERROR,"Hubo un error al cargar el archivo a PipeSponsor: "
                    + availableRuts.toString());
        } catch (PreIngresoException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PREINGRESO, e.getMessage(), e, null);
            LOGGER.error(e.getMessage(), e);
        } catch (PipeSponsorDBException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PREINGRESO, e.getMessage(), e, null);
            LOGGER.error(e.getMessage(), e);
        } catch (FileTypeException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, e.getMessage(), e, null);
            LOGGER.error(e.getMessage(), e);
        } catch (JAXBException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PIPESPONSOR, e.getMessage(), e, null);
            LOGGER.error(e.getMessage(), e);
        } catch (SQLException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PIPESPONSOR, e.getMessage(), e, null);
            LOGGER.error(e.getMessage(), e);
        }

        return new ProcessResult(ProcessResult.STATUS_ERROR,"Hubo un error al procesar el archivo. Consulte con su Admin.");
    }

    private void processRowsAfterPipeLoad(Process process, String brokerRut, Long fileTypeId, Lot persistedLot, String user)
                                        throws PreIngresoException, PipeSponsorDBException, JAXBException, SQLException {

        if (fileTypeId.equals(FileType.ALTA_TITULAR)) {
            List<AltaTitular> list = synchronizeAltaTit(persistedLot);
            processAltasTitular(process, list, user);

        } else if (fileTypeId.equals(FileType.ALTA_CARGA)) {
            List<AltaCarga> list = synchronizeAltaCar(persistedLot);
            processAltasCargas(process, list, user);

        } else if (fileTypeId.equals(FileType.BAJA_TITULAR)) {
            List<ExclusionTitular> list = synchronizeExcTit(persistedLot);
            processExclusionTitular(process, list, user);

        } else if (fileTypeId.equals(FileType.BAJA_CARGA)) {
            List<ExclusionCarga> list = synchronizeExcCarga(persistedLot);
            processExclusionCarga(process, list, user);
        }

    }

    private List<AltaTitular> synchronizeAltaTit(Lot persistedLot) throws PipeSponsorDBException {
        List<AltaTitular> list;

        do {
            list = pipeTablesManager.findAltaTitUnprocessedRows(persistedLot.getId());

            if (waitingToGetListAgain(list)) break;

        } while(list.isEmpty());

        return list;
    }

    private boolean waitingToGetListAgain(List list) {
        if(list.isEmpty()){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                LOGGER.error("Errorsito GeGeWe con threads");
                Thread.currentThread().interrupt();
            }
        } else {
            return true;
        }
        return false;
    }

    private List<AltaCarga> synchronizeAltaCar(Lot persistedLot) throws PipeSponsorDBException {
        List<AltaCarga> list;
        do {
            list = pipeTablesManager.findAltaCarUnprocessedRows(persistedLot.getId());

            if (waitingToGetListAgain(list)) break;

        } while(list.isEmpty());

        return list;
    }

    private List<ExclusionTitular> synchronizeExcTit(Lot persistedLot) throws PipeSponsorDBException {
        List<ExclusionTitular> list;
        do {
            list = pipeTablesManager.findExcTitUnprocessedRows(persistedLot.getId());

            if (waitingToGetListAgain(list)) break;

        } while(list.isEmpty());

        return list;
    }

    private List<ExclusionCarga> synchronizeExcCarga(Lot persistedLot) throws PipeSponsorDBException {
        List<ExclusionCarga> list;
        do {
            list = pipeTablesManager.findExcCarUnprocessedRows(persistedLot.getId());

            if (waitingToGetListAgain(list)) break;

        } while(list.isEmpty());

        return list;
    }


    private void processExclusionCarga(Process process, List<ExclusionCarga> list, String user) throws JAXBException, SQLException, PreIngresoException, PipeSponsorDBException {
        int index = 0;

        HashMap<Long, Lot> lotsCreated = new HashMap<Long, Lot>();
        int counter = 0;
        for (ExclusionCarga exclusionCarga : list) {
            try {
                Lot lot = null;
                // Crear un lote nuevo con el registro de lote altatitular
                if(!lotsCreated.containsKey(exclusionCarga.getLote())){
                    lot = lotManager.create(new Lot(new Date(), FileType.BAJA_CARGA, process.getId(), "WS_" + format.format(new Date()), exclusionCarga.getLote().toString(), LotStatus.FINALIZED));
                    lotsCreated.put(exclusionCarga.getLote(), lot);
                } else {
                    lot = lotsCreated.get(exclusionCarga.getLote());
                }

                Detail objToCreate = detailManager.map(exclusionCarga, lot, process.isConsiderBarcodes(), ++counter);
                objToCreate.setStatusId(DetailsStatus.FINALIZED);
                Detail detail = detailManager.create(objToCreate);

                preIngresoManager.createAlta(lot, detail, process, user);
            } catch (Exception e){
                logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al procesar la baja " + exclusionCarga.toString(), null);
            }
        }

        changeExcCarToProcessed(list);
    }

    private void processExclusionTitular(Process process, List<ExclusionTitular> list, String user) throws JAXBException, SQLException, PreIngresoException, PipeSponsorDBException {
        int index = 0;

        HashMap<Long, Lot> lotsCreated = new HashMap<Long, Lot>();
        int counter = 0;
        for (ExclusionTitular exclusionTitular : list) {
            try {
                Lot lot = null;
                // Crear un lote nuevo con el registro de lote altatitular
                if(!lotsCreated.containsKey(exclusionTitular.getLote())){
                    lot = lotManager.create(new Lot(new Date(), FileType.BAJA_TITULAR, process.getId(), "WS_" + format.format(new Date()), exclusionTitular.getLote().toString(), LotStatus.FINALIZED));
                    lotsCreated.put(exclusionTitular.getLote(), lot);
                } else {
                    lot = lotsCreated.get(exclusionTitular.getLote());
                }

                Detail objToCreate = detailManager.map(exclusionTitular, lot, process.isConsiderBarcodes(), ++counter);
                objToCreate.setStatusId(DetailsStatus.FINALIZED);
                Detail detail = detailManager.create(objToCreate);

                preIngresoManager.createAlta(lot, detail, process, user);
            } catch (Exception e){
                logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al procesar la baja " + exclusionTitular.toString(), null);
            }
        }

        changeExcTitToProcessed(list);
    }

    private void processAltasCargas(Process process, List<AltaCarga> list, String user) throws JAXBException, SQLException, PreIngresoException, PipeSponsorDBException {
        int index = 0;

        HashMap<Long, Lot> lotsCreated = new HashMap<Long, Lot>();
        int counter = 0;
        for (AltaCarga altaCarga : list) {
            try {
                Lot lot = null;
                // Crear un lote nuevo con el registro de lote altatitular
                if(!lotsCreated.containsKey(altaCarga.getLote())){
                    lot = lotManager.create(new Lot(new Date(), FileType.ALTA_CARGA, process.getId(), "WS_" + format.format(new Date()), altaCarga.getLote().toString(), LotStatus.FINALIZED));
                    lotsCreated.put(altaCarga.getLote(), lot);
                } else {
                    lot = lotsCreated.get(altaCarga.getLote());
                }

                Detail objToCreate = detailManager.map(altaCarga, lot, process.isConsiderBarcodes(), ++counter);
                objToCreate.setStatusId(DetailsStatus.FINALIZED);
                Detail detail = detailManager.create(objToCreate);

                preIngresoManager.createAlta(lot, detail, process, user);
            } catch (Exception e){
                logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al procesar el alta " + altaCarga.toString(), null);
            }
        }

        changeAltaCarToProcessed(list);
    }

    private void processAltasTitular(Process process, List<AltaTitular> list, String user) throws JAXBException, SQLException, PreIngresoException, PipeSponsorDBException {
        int index = 0;

        HashMap<Long, Lot> lotsCreated = new HashMap<Long, Lot>();
        int counter = 0;
        for (AltaTitular altaTitular : list) {
            try {
                Lot lot = null;
                // Crear un lote nuevo con el registro de lote altatitular
                if(!lotsCreated.containsKey(altaTitular.getLote())){
                    lot = lotManager.create(new Lot(new Date(), FileType.ALTA_TITULAR, process.getId(), "WS_" + format.format(new Date()), altaTitular.getLote().toString(), LotStatus.FINALIZED));
                    lotsCreated.put(altaTitular.getLote(), lot);
                } else {
                    lot = lotsCreated.get(altaTitular.getLote());
                }

                Detail objToCreate = detailManager.map(altaTitular, lot, process.isConsiderBarcodes(), ++counter);
                objToCreate.setStatusId(DetailsStatus.FINALIZED);
                Detail detail = detailManager.create(objToCreate);

                preIngresoManager.createAlta(lot, detail, process, user);
            } catch (Exception e){
                logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al procesar el alta " + altaTitular.toString(), null);
            }
        }

        changeAltaTitToProcessed(list);
    }

    private boolean callToPipeSponsor(String brokerRut, Process process, String fileName, Long pipeSponsorId, Lot persistedLot, byte[] fileBytesFormatted) throws JAXBException, SQLException, PipeSponsorException {
        boolean response = false;
        try {
            response = pipeSponsorManager.sendPipeSposor(process, fileName, fileBytesFormatted, pipeSponsorId, persistedLot, null);
        } catch (PipeSponsorException e) {
            throw new PipeSponsorException(e.getMessage());
        }

        return response;
    }

    private byte[] fixFile(String str, Long lotId) {
        String[] rows = str.split("\r\n");
        StringBuilder finalString = new StringBuilder();

        finalString.append(rows[0].concat("Lot Id;\r\n"));
        for (int j = 0; j < rows.length; j++) {
            if(j == 0)
                continue;

            finalString.append(rows[j] + lotId + ";" + "\r\n");
        }

        return finalString.toString().getBytes(Charset.forName("UTF-8"));
    }

    private void changeAltaTitToProcessed(List<AltaTitular> list) throws PipeSponsorDBException {
        for (AltaTitular altaTitular : list) {
            pipeTablesManager.updateStatusAltaTit(altaTitular.getLotId(), "1");
        }
    }

    private void changeAltaCarToProcessed(List<AltaCarga> list) throws PipeSponsorDBException {
        for (AltaCarga altaCarga : list) {
            pipeTablesManager.updateStatusAltaTit(altaCarga.getLotId(), "1");
        }
    }

    private void changeExcTitToProcessed(List<ExclusionTitular> list) throws PipeSponsorDBException {
        for (ExclusionTitular exclusionTitular : list) {
            pipeTablesManager.updateStatusAltaTit(exclusionTitular.getLotId(), "1");
        }
    }

    private void changeExcCarToProcessed(List<ExclusionCarga> list) throws PipeSponsorDBException {
        for (ExclusionCarga exclusionCarga : list) {
            pipeTablesManager.updateStatusAltaTit(exclusionCarga.getLotId(), "1");
        }
    }

    /**
     * This method get the File Definition ID Param to send files into PipeSponsor.
     * @param process is the current process
     * @param fileTypeId is the file type id.
     * @return fileDefinitionId.
     */
    private Long getPipeSponsorId(Process process, Long fileTypeId) {
        if(fileTypeId.equals(FileType.ALTA_TITULAR))
            return Long.valueOf(process.getFileDefinitionIdAltaTit());
        else if(fileTypeId.equals(FileType.ALTA_CARGA))
            return Long.valueOf(process.getFileDefinitionIdAltaCar());
        else if(fileTypeId.equals(FileType.BAJA_TITULAR))
            return Long.valueOf(process.getFileDefinitionIdExcTit());
        else
            return Long.valueOf(process.getFileDefinitionIdExcCar());
    }

    private Long getFileTypeIdByWsId(String movementType) throws FileTypeException {
        List<FileType> fileTypes = fileTypeManager.findAll();

        for (FileType fileType : fileTypes) {
            if(fileType.getWsId().equals(movementType))
                return fileType.getId();
        }

        throw new FileTypeException("El tipo de archivo es inválido. Identificadores reconocidos: " + fileTypes.toString() + ".");
    }

}
