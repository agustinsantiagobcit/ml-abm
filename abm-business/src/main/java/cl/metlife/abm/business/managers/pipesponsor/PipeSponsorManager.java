package cl.metlife.abm.business.managers.pipesponsor;

import cl.metlife.abm.business.managers.LogbookManager;
import cl.metlife.abm.business.managers.pipesponsor.exception.PipeSponsorException;
import cl.metlife.abm.domain.Log;
import cl.metlife.abm.domain.Lot;
import cl.metlife.abm.domain.Process;
import cl.metlife.abm.domain.AltaCarga;
import cl.metlife.abm.domain.AltaTitular;
import cl.metlife.abm.domain.ExclusionCarga;
import cl.metlife.abm.domain.ExclusionTitular;
import cl.metlife.abm.persistence.dao.AltaCargaDAO;
import cl.metlife.abm.persistence.dao.AltaTitularDAO;
import cl.metlife.abm.persistence.dao.ExclusionCargaDAO;
import cl.metlife.abm.persistence.dao.ExclusionTitularDAO;
import cl.metlife.abm.persistence.dao.exception.PipeSponsorDBException;
import cl.metlife.ws.clients.pipesponsor.*;
import org.apache.commons.io.FilenameUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PipeSponsorManager {

    @EJB
    PipeSponsorServiceManager pipeSponsorServiceManager;

    @EJB
    LogbookManager logbookManager;

    @EJB
    AltaTitularDAO altaTitularDAO;

    @EJB
    AltaCargaDAO altaCargaDAO;

    @EJB
    ExclusionTitularDAO exclusionTitularDAO;

    @EJB
    ExclusionCargaDAO exclusionCargaDAO;


    public boolean sendPipeSposor(Process process, String filename, byte[] file, Long fileDefinitionId, Lot lot, String username) throws JAXBException, SQLException, PipeSponsorException {
        LogProcessDTO logDataDTO = null;

        try {
            ObjectFactory fact = new ObjectFactory();

            SponsorRemoteFiles fileToUpload = fact.createSponsorRemoteFiles();

            fileToUpload.setSponsorCode(fact.createSponsorRemoteFilesSponsorCode(filename.split("_")[0]));
            fileToUpload.setFileDefinitionId(fileDefinitionId);
            fileToUpload.setExtension(fact.createSponsorRemoteFilesExtension(FilenameUtils.getExtension(filename)));
            fileToUpload.setFileName(fact.createSponsorRemoteFilesFileName(filename));

            fileToUpload.setFileBuffer(fact.createSponsorRemoteFilesFileBuffer(file));

            ArrayOfSponsorRemoteFiles arrayOfSponsorRemoteFiles = fact.createArrayOfSponsorRemoteFiles();
            arrayOfSponsorRemoteFiles.getSponsorRemoteFiles().add(fileToUpload);

            logDataDTO = pipeSponsorServiceManager.getService().setSponsorFiles(arrayOfSponsorRemoteFiles);

            if (logDataDTO==null){
                throw new PipeSponsorException("El servicio de PipeSponsor tiene problemas de sincronía. Favor consulte a su Administrador.");
            }

            List<String> errorFileContent = new ArrayList<String>();
            if (logDataDTO!=null && logDataDTO.getLogProcessFiles()!=null){
                logDataDTO.getLogProcessFiles().getValue().getLogProcessFileDTO();
                for (LogProcessFileDTO dto : logDataDTO.getLogProcessFiles().getValue().getLogProcessFileDTO()){

                    String logFileName = dto.getFileOrgName().getValue();
                    for (LogProcessDataDTO logProcessData: dto.getLogProcessDatas().getValue().getLogProcessDataDTO()){
                        //verifico si hay algun error al momento de procesar el archivo
                        if (logProcessData.getErrDescription().getValue().length()>0){
                            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PIPESPONSOR, "Hubo un error en el archivo " + filename
                                    + ", Fila: " + +logProcessData.getProcessDataRow() + ", Columna: " + logProcessData.getProcessDataCol().getValue() + ".", lot,  null, username);

                            errorFileContent.add("Archivo: "+logFileName+" Columna: "+logProcessData.getProcessDataCol().getValue() +" Fila: "+
                                    logProcessData.getProcessDataRow() +" Error :" +logProcessData.getErrDescription());
                        }
                    }
                }
                if (errorFileContent.size()>0)
                    return false;
            } else {
                logbookManager.info(process, Log.LOG_STEP_PIPESPONSOR, "Se procesó con éxito el archivo " + filename, null);
                return true;
            }
        } catch (Exception e) {
            throw new PipeSponsorException("Ocurrió un error al cargar el archivo " + filename, e);
        }

        logbookManager.info(process, Log.LOG_STEP_PIPESPONSOR, "Se procesó con éxito el archivo " + filename, null);
        return true;
    }

    public List<AltaTitular> findAltaTitularByBroker(String broker) throws PipeSponsorDBException {
        return altaTitularDAO.findPipesponsorFileByBrokerRut(broker);
    }

    public List<AltaCarga> findAltaCargaByBroker(String broker) throws PipeSponsorDBException {
        return altaCargaDAO.findPipesponsorFileByBrokerRut(broker);
    }

    public List<ExclusionTitular> findExclusionTitularByBroker(String broker) throws PipeSponsorDBException {
        return exclusionTitularDAO.findPipesponsorFileByBrokerRut(broker);
    }

    public List<ExclusionCarga> findExclusionCargaByBroker(String broker) throws PipeSponsorDBException {
        return exclusionCargaDAO.findPipesponsorFileByBrokerRut(broker);
    }

}