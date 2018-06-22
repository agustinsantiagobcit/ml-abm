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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Stateless
public class ProcessExecutionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessExecutionManager.class);

    @EJB
    private MailSendlManager mailSendlManager;

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


    @Asynchronous
    public void execute(Process process, String executionType) {
        execute(process, null, executionType);
    }

    @Asynchronous
    public void execute(Process process, String username, String executionType) {
        final String brokerRut = process.getBrokerRut().replace(".", "");

        logbookManager.info(process, Log.LOG_STEP_EJECUCION_GENERAL ,"Iniciando Proceso " + executionType + " " +
                (process.getProcessTypeId().equals(ProcessType.PROCESS_TYPE_NORMAL) ? "Corredor ": " Multicorredor")
                + " " + process.getBrokerName() + " (" + process.getBrokerRut() + ")" , username);

        try{
            logbookManager.info(process, Log.LOG_STEP_EJECUCION_GENERAL ,"Se procederá a conectar con servidor SFTP " + process.getHost()+":"+process.getPort()+"/"+process.getUser(), username);

            // Check SFTP and local folders. (Create, Move, Delete)
            checkFolders(process, brokerRut);

            // Getting the list of .csv in broker folder.
            List<String> currentExcelFiles = localFolderManager.listCSVFilesByRut(brokerRut);

            if(currentExcelFiles != null){
                if(!currentExcelFiles.isEmpty()){
                    logbookManager.info(process, Log.LOG_STEP_SFTP, "Conexión Establecida", username);

                    int brokerFilesCount = countBrokerFiles(currentExcelFiles, brokerRut);

                    if(brokerFilesCount > 0){
                        logbookManager.info(process, Log.LOG_STEP_SFTP, "Se encontraron " + currentExcelFiles.size() + " archivos en la carpeta SFTP. Se procesarán " + brokerFilesCount + " de " + currentExcelFiles.size() + " archivos.", username);

                        currentExcelFiles = getOnlyFilesOfBroker(currentExcelFiles, brokerRut);

                        int csvCount = 1;
                        for (String currentExcelFile : currentExcelFiles) {
                            String currentExcelFileAfterDownload = Lot.PROCESSING_SUFFIX + currentExcelFile;
                            Long fileTypeId = getFileTypeIdByFilenameAndValidate(currentExcelFile);
                            Long pipeSponsorId = getPipeSponsorId(process, fileTypeId);

                            String lot = getLotByFilename(currentExcelFile);
                            Lot persistedLot = lotManager.create(new Lot(new Date(), fileTypeId, process.getId(), currentExcelFile,lot));

                            byte[] fileBytes = localFolderManager.getFile(brokerRut, currentExcelFile, SFTPManager.EXCEL_FOLDER_NAME);
                            byte[] fileBytesFormatted = fixFile(new String(fileBytes), persistedLot.getId());

                            logbookManager.info(process, Log.LOG_STEP_SFTP, csvCount + "/" + currentExcelFiles.size() +  " Se obtuvo el archivo " + currentExcelFile + " correctamente.", username);

                            // Uploading file into Pipe.
                            boolean pipeResponse = callToPipeSponsor(brokerRut, process, username, currentExcelFile, pipeSponsorId, persistedLot, fileBytesFormatted, currentExcelFileAfterDownload);

                            // Processing data after be uploaded in Pipe.
                            processRowsAfterPipeLoad(process, brokerRut, currentExcelFile, fileTypeId, persistedLot, currentExcelFileAfterDownload);

                            checkErrorsAndSendMail(persistedLot, process);
                            csvCount++;
                        }

                        // Enviar email si hay errores.
                    } else {
                        logbookManager.info(process, Log.LOG_STEP_SFTP, "No se encontraron archivos .csv del corredor para procesar.", username);
                    }

                } else {
                    logbookManager.info(process, Log.LOG_STEP_SFTP, "No se encontraron archivos .csv para procesar.", username);
                }

            } else {
                logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_SFTP, "Consulte con su administrador. Código GG0001", username);
            }

        } catch (SFTPConnectionException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_SFTP, e.getMessage(), e, username);
            LOGGER.error(e.getMessage(), e);
        } catch (PipeSponsorException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PIPESPONSOR, e.getMessage(), e, username);
            LOGGER.error(e.getMessage(), e);
        } catch (PreIngresoException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PREINGRESO, e.getMessage(), e, username);
            LOGGER.error(e.getMessage(), e);
        } catch (PipeSponsorDBException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PREINGRESO, e.getMessage(), e, username);
            LOGGER.error(e.getMessage(), e);
        } catch (FileTypeException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, e.getMessage(), e, username);
            LOGGER.error(e.getMessage(), e);
        } catch (JAXBException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PIPESPONSOR, e.getMessage(), e, username);
            LOGGER.error(e.getMessage(), e);
        } catch (SQLException e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PIPESPONSOR, e.getMessage(), e, username);
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void checkErrorsAndSendMail(Lot processed, Process process) {
        List<Log> logs = logbookManager.findByProcessIdAndStatus(processed.getProcessId(), processed.getId(), Log.LOG_LEVEL_ERROR);

        try{
            if(!logs.isEmpty()){
                mailSendlManager.sendMail(processed, logs, process);
                logbookManager.log(process, Log.LOG_LEVEL_INFO, Log.LOG_STEP_EJECUCION_GENERAL, "Se envió email con errores al correo: " + processed.getProcess().getErrorMail() + ". Lotname: " + processed.getFilename(), processed, null, null, null);
            } else
                LOGGER.debug("No hay logs de error para ser enviados.");
        } catch (Exception e){
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al enviar correo con errores", processed, null, e, null);
        }
    }

    private void checkFolders(Process process, String rut) throws SFTPConnectionException {
        // Checking if folders are created
        sftpManager.checkFolders(rut, process);

        // Checking if local folders are created
        String localFolder = localFolderManager.checkFoldersAndReturnLocalPath(rut);

        // Moving excel files from SFTP to local.
        sftpManager.moveFromSftpToLocalByFolderNameAndDelete(process, process.getBrokerRut().replace(".", ""), SFTPManager.EXCEL_FOLDER_NAME, localFolder);

        // Moving pdf files from SFTP to local.
        sftpManager.moveFromSftpToLocalByFolderNameAndDelete(process, process.getBrokerRut().replace(".", ""), SFTPManager.PDF_FOLDER_NAME, localFolder);
    }

    private void processRowsAfterPipeLoad(Process process, String brokerRut, String currentExcelFile, Long fileTypeId, Lot persistedLot,
                                          String currentExcelFileAfterDownload) throws SFTPConnectionException, PreIngresoException, PipeSponsorDBException, JAXBException, SQLException {
        // Registrar detalle local y enviar a preingreso.
        Integer rowsProcessed = 0;

        if (fileTypeId.equals(FileType.ALTA_TITULAR)) {
            List<AltaTitular> list = synchronizeAltaTit(persistedLot);
            processAltasTitular(process, persistedLot, rowsProcessed, list);

        } else if (fileTypeId.equals(FileType.ALTA_CARGA)) {
            List<AltaCarga> list = synchronizeAltaCar(persistedLot);
            processAltasCargas(process, persistedLot, rowsProcessed, list);

        } else if (fileTypeId.equals(FileType.BAJA_TITULAR)) {
            List<ExclusionTitular> list = synchronizeExcTit(persistedLot);
            processExclusionTitular(process, persistedLot, rowsProcessed, list);

        } else if (fileTypeId.equals(FileType.BAJA_CARGA)) {
            List<ExclusionCarga> list = synchronizeExcCarga(persistedLot);
            processExclusionCarga(process, persistedLot, rowsProcessed, list);
        }

        persistedLot.setLotStatusId(((process.isConsiderBarcodes()) ? LotStatus.LOADED_IN_PIPESPONSOR: LotStatus.FINALIZED));
        lotManager.update(persistedLot);
        // Si no falla, Se marca como Procesado_
        localFolderManager.renameFile(brokerRut, currentExcelFileAfterDownload, Lot.SUCCESSFUL_SUFFIX + currentExcelFile, SFTPManager.EXCEL_FOLDER_NAME);
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


    private void processExclusionCarga(Process process, Lot persistedLot, Integer rowsProcessed, List<ExclusionCarga> list) throws JAXBException, SQLException, PreIngresoException, PipeSponsorDBException {
        int index = 0;
        for (ExclusionCarga exclusionCarga : list) {
            try {
                Detail detail = detailManager.create(detailManager.map(exclusionCarga, persistedLot, process.isConsiderBarcodes(), ++index));

                boolean response = preIngresoManager.createBaja(persistedLot, detail, process, null);
                if(response) {rowsProcessed++; updateDetailStatus(detail, DetailsStatus.FINALIZED);}
                else {updateDetailStatus(detail, DetailsStatus.ERROR); }
            } catch (Exception e){
                logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al procesar el alta " + exclusionCarga.toString(), null);
            }
        }

        changeExcCarToProcessed(list);
    }

    private void processExclusionTitular(Process process, Lot persistedLot, Integer rowsProcessed, List<ExclusionTitular> list) throws JAXBException, SQLException, PreIngresoException, PipeSponsorDBException {
        int index = 0;
        for (ExclusionTitular exclusionTitular : list) {
            try {
                Detail detail = detailManager.create(detailManager.map(exclusionTitular, persistedLot, process.isConsiderBarcodes(), ++index));

                boolean response = preIngresoManager.createBaja(persistedLot, detail, process, null);
                if(response) {rowsProcessed++; updateDetailStatus(detail, DetailsStatus.FINALIZED);}
                else {updateDetailStatus(detail, DetailsStatus.ERROR); }
            } catch (Exception e){
                logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al procesar el alta " + exclusionTitular.toString(), null);
            }
        }

        changeExcTitToProcessed(list);
    }

    private void processAltasCargas(Process process, Lot persistedLot, Integer rowsProcessed, List<AltaCarga> list) throws JAXBException, SQLException, PreIngresoException, PipeSponsorDBException {
        int index = 0;
        for (AltaCarga altaCarga : list) {
            try {
                Detail detail = detailManager.create(detailManager.map(altaCarga, persistedLot, process.isConsiderBarcodes(), ++index));

                boolean response = preIngresoManager.createAlta(persistedLot, detail, process, null);
                if(response) {rowsProcessed++; updateDetailStatus(detail, DetailsStatus.FINALIZED);}
                else {updateDetailStatus(detail, DetailsStatus.ERROR); }
            } catch (Exception e){
                logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al procesar el alta " + altaCarga.toString(), null);
            }
        }

        changeAltaCarToProcessed(list);
    }

    private void processAltasTitular(Process process, Lot persistedLot, Integer rowsProcessed, List<AltaTitular> list) throws JAXBException, SQLException, PreIngresoException, PipeSponsorDBException {
        int index = 0;
        for (AltaTitular altaTitular : list) {
            try {
                Detail objToCreate = detailManager.map(altaTitular, persistedLot, process.isConsiderBarcodes(), ++index);

                Detail detail = detailManager.create(objToCreate);
                preIngresoManager.createAlta(persistedLot, detail, process, null);
            } catch (Exception e){
                logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_EJECUCION_GENERAL, "Hubo un error al procesar el alta " + altaTitular.toString(), null);
            }
        }

        changeAltaTitToProcessed(list);
    }

    private void updateDetailStatus(Detail detail, Long statusId) {
        detail.setStatusId(statusId);
        detailManager.update(detail);
    }

    private boolean callToPipeSponsor(String brokerRut, Process process, String username, String currentExcelFile, Long pipeSponsorId, Lot persistedLot, byte[] fileBytesFormatted, String currentExcelFileAfterDownload) throws JAXBException, SQLException, SFTPConnectionException, PipeSponsorException {
        boolean response = false;
        try {
            response = pipeSponsorManager.sendPipeSposor(process, currentExcelFile, fileBytesFormatted, pipeSponsorId, persistedLot, username);
        } catch (PipeSponsorException e) {
            // Si falla, se marca como Error_
            localFolderManager.renameFile(brokerRut, currentExcelFileAfterDownload, Lot.ERROR_SUFFIX + currentExcelFile, SFTPManager.EXCEL_FOLDER_NAME);
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
                continue;;

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

    /**
     * This method return the lot number of filename.
     * @param currentExcelFile
     * @return lot number String.
     */
    private String getLotByFilename(String currentExcelFile) {
        String[] splittedName = currentExcelFile.split("_");

        return splittedName[2];
    }

    private Long getFileTypeIdByFilenameAndValidate(String currentExcelFile) throws FileTypeException {
        currentExcelFile = currentExcelFile.replace(".csv", "");
        String[] splittedName = currentExcelFile.split("_");
        String response = "";

        if(splittedName.length == 5)
            response = splittedName[4];
        else
            response = splittedName[4] + "_" + splittedName[5];

        List<FileType> fileTypes = fileTypeManager.findAll();

        String possibleErrorMessage = "";
        for (FileType fileType : fileTypes) {
            possibleErrorMessage += fileType.getFileId() + ", ";

            if(fileType.getFileId().equals(response))
                return fileType.getId();
        }

        possibleErrorMessage = possibleErrorMessage.substring(0, possibleErrorMessage.length() - 2);
        throw new FileTypeException("El tipo de archivo es inválido. Identificadores reconocidos: " + possibleErrorMessage + ".");
    }

    private List<String> getOnlyFilesOfBroker(List<String> currentExcelFiles, String rut) {
        List<String> brokerList = new ArrayList<String>();

        for (String currentExcelFile : currentExcelFiles) {
            if(currentExcelFile.startsWith(rut.replace("-", "")))
                brokerList.add(currentExcelFile);
        }

        return brokerList;
    }

    private int countBrokerFiles(List<String> currentExcelFiles, String rut) {
        int count = 0;

        for (String currentExcelFile : currentExcelFiles) {
            String[] splitted = currentExcelFile.split("_");

            if(splitted[0].equals(rut.replace("-", "")))
                count++;
        }

        return count;
    }

}
