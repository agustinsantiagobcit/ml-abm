package cl.metlife.abm.business.managers.localfolder;

import cl.metlife.abm.business.execution.ProcessExecutionManager;
import cl.metlife.abm.business.managers.ABMConfigurationManager;
import cl.metlife.abm.business.managers.sftp.SFTPManager;
import cl.metlife.abm.domain.ABMConfiguration;
import cl.metlife.abm.domain.Lot;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class LocalFolderManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalFolderManager.class);

    @EJB
    private ABMConfigurationManager configurationManager;


    public String checkFoldersAndReturnLocalPath(String rut)  {
        try {
            String localFolderRoot = configurationManager.getByKey(ABMConfiguration.LOCAL_FOLDER_ROOT).getValor();
            File localFolderRootFile = new File(localFolderRoot);
            if(!localFolderRootFile.exists()) localFolderRootFile.mkdirs();

            File rutFolder = new File(localFolderRoot + rut + "\\");
            if(!rutFolder.exists()) rutFolder.mkdirs();

            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            File environmentFile = new File(localFolderRoot + rut + "\\" + environmentFolder + "\\");
            if(!environmentFile.exists()) environmentFile.mkdirs();

            File excelFolder = new File(localFolderRoot + rut + "\\" + environmentFolder + "\\" + SFTPManager.EXCEL_FOLDER_NAME + "\\");
            if(!excelFolder.exists()) excelFolder.mkdirs();

            File pdfFolder = new File(localFolderRoot + rut + "\\" + environmentFolder + "\\" + SFTPManager.PDF_FOLDER_NAME + "\\");
            if(!pdfFolder.exists()) pdfFolder.mkdirs();

            return localFolderRoot + rut + "\\" + environmentFolder + "\\";
        } catch (Exception e) {
            LOGGER.error("Hubo un error al checkear las carpetas locales", e);
        }

        return null;
    }

    /**
     * This method return a list of .csv files, from @rut/EXCEL/ folder in local.
     * @param rut
     * @return list of .csv in rut/excel folder.
     */
    public List<String> listCSVFilesByRut(String rut) {
        List<String> returnList = new ArrayList<String>();

        try {
            String localFolderRoot = configurationManager.getByKey(ABMConfiguration.LOCAL_FOLDER_ROOT).getValor();
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();

            File excelFolder = new File(localFolderRoot + rut + "\\" + environmentFolder + "\\" + SFTPManager.EXCEL_FOLDER_NAME + "\\");

            for (File file : excelFolder.listFiles()) {
                returnList.add(file.getName());
            }

            return returnList;
        } catch (Exception e) {
            LOGGER.error("Hubo un error al listar los archivos csv.", e);
        }

        return null;
    }

    public byte[] getFile(String brokerRut, String currentExcelFile, String fileTypeFolder) {
        FileInputStream stream = null;

        try {
            String localFolderRoot = configurationManager.getByKey(ABMConfiguration.LOCAL_FOLDER_ROOT).getValor();
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();

            File file = new File(localFolderRoot + brokerRut + "\\" + environmentFolder + "\\" + fileTypeFolder + "\\" + currentExcelFile);

            stream = new FileInputStream(file);
            byte[] bytes = IOUtils.toByteArray(stream);
            stream.close();

            file.renameTo(new File(localFolderRoot + brokerRut + "\\" + environmentFolder + "\\" + fileTypeFolder + "\\" + Lot.PROCESSING_SUFFIX + currentExcelFile));

            return bytes;
        } catch (Exception e) {
            LOGGER.error("Hubo un error al obtener archivo csv y cambiar nombre.", e);
        }

        return null;
    }

    public void renameFile(String brokerRut, String fromFileName, String toFileNAme, String folderTypeName) {
        FileInputStream stream = null;

        try {
            String localFolderRoot = configurationManager.getByKey(ABMConfiguration.LOCAL_FOLDER_ROOT).getValor();
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();

            File file = new File(localFolderRoot + brokerRut + "\\" + environmentFolder + "\\" + folderTypeName + "\\" + fromFileName);
            file.renameTo(new File(localFolderRoot + brokerRut + "\\" + environmentFolder + "\\" + folderTypeName + "\\" + toFileNAme));
        } catch (Exception e) {
            LOGGER.error("Hubo un error al renombrar el archivo a ERROR_.", e);
        }
    }

    public boolean existPDFFile(String brokerRut, String pdfPath){
        try {
            String localFolderRoot = configurationManager.getByKey(ABMConfiguration.LOCAL_FOLDER_ROOT).getValor();
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();

            File file = new File(localFolderRoot + brokerRut + "\\" + environmentFolder + "\\" + SFTPManager.PDF_FOLDER_NAME + "\\" + pdfPath);

            return file.exists();
        } catch (Exception e) {
            LOGGER.error("Hubo un error al conocer si un PDF existe en la carpeta local.", e);
        }

        return false;
    }
}
