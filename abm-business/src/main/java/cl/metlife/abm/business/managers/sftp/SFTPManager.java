package cl.metlife.abm.business.managers.sftp;

import cl.metlife.abm.business.execution.ProcessExecutionManager;
import cl.metlife.abm.business.managers.ABMConfigurationManager;
import cl.metlife.abm.business.managers.sftp.exception.SFTPConnectionException;
import cl.metlife.abm.domain.ABMConfiguration;
import cl.metlife.abm.domain.Process;
import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import static com.jcraft.jsch.ChannelSftp.SSH_FX_NO_SUCH_FILE;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class SFTPManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessExecutionManager.class);
    private static final int SFTP_TIMEOUT = 3600000;
    public static final String EXCEL_FOLDER_NAME = "EXCEL";
    public static final String PDF_FOLDER_NAME = "PDF";
    private static final String RESULTS_FOLDER_NAME = "RESULTADOS";


    @EJB
    ABMConfigurationManager configurationManager;


    /**
     * This method download all contained in <code>folderName</code> to local.
     */
    public void moveFromSftpToLocalByFolderNameAndDelete(Process process, String rut, String folderName, String localFolder) throws SFTPConnectionException {
        List<String> returnList = new ArrayList<String>();
        Session session = null;
        JSch jsch = new JSch();
        ChannelSftp sftp = new ChannelSftp();

        try {
            session = getConnectedSession(process.getUser(), process.getPassword(), process.getHost(), Integer.valueOf(process.getPort()));
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            // Enter to rut folder
            sftp.cd(rut);

            // Environment folder.
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            sftp.cd(environmentFolder);

            // Enter to folderName
            sftp.cd(folderName);

            //sftp list files
            Vector<ChannelSftp.LsEntry> list = sftp.ls((folderName.equals(SFTPManager.EXCEL_FOLDER_NAME)) ? "*.csv": "*.pdf");
            for(ChannelSftp.LsEntry entry : list) {
                sftp.get(entry.getFilename(), localFolder + folderName + "\\" + entry.getFilename());
                LOGGER.debug("Archivo descargado: " + entry.getFilename());
                deleteFileFromSFTP(sftp, entry.getFilename());
            }

        } catch (JSchException e) {
            throw new SFTPConnectionException("Hubo un error al conectar al SFTP", e);
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if(sftp!=null)
                sftp.disconnect();
            if(session != null)
                session.disconnect();
        }

    }

    public void deleteFileFromSFTP(ChannelSftp sftp, String deleteFile) {
        try {
            LOGGER.info("Se eliminará el archivo del SFTP");
            sftp.rm(deleteFile);
            LOGGER.info("Archivo eliminado correctamente");
        } catch (Exception e) {
            LOGGER.error("Hubo problemas al eliminar el archivo desde el SFTP");
        }
    }

    public SFTPResponse testSFTP(String host, int port, String user, String password) throws SFTPConnectionException {
        SFTPResponse sftpResponse = null;
        Session session = null;
        try {
            session = getSession(user, host, port);
            UserInfo ui = new SUserInfo(password, null);
            session = settingSFTPParameters(session, password, ui, SFTP_TIMEOUT);

            session.connect();
        } catch (JSchException e) {
            sftpResponse = new SFTPResponse(false, e.getMessage());
            e.printStackTrace();

            return sftpResponse;
        } finally {
            sftpResponse = new SFTPResponse(true, null);
            session.disconnect();
        }

        return sftpResponse;
    }

    public SFTPResponse createDefaultFoldersBySFTPParameters(String host, int port, String user, String password, String rut) throws SFTPConnectionException {
        ChannelSftp sftp = new ChannelSftp();

        SFTPResponse sftpResponse = null;
        Session session = null;
        try {
            session = getConnectedSession(user, password, host, port);

            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            String firstFolder = rut.replace(".", "");
            sftp.mkdir(firstFolder);
            sftp.cd(firstFolder);

            // Environment folder.
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            sftp.mkdir(environmentFolder);
            sftp.cd(environmentFolder);

            sftp.mkdir(EXCEL_FOLDER_NAME);
            sftp.mkdir(PDF_FOLDER_NAME);
            sftp.mkdir(RESULTS_FOLDER_NAME);

        } catch (JSchException e) {
            sftpResponse = new SFTPResponse(false, e.getMessage());
            e.printStackTrace();

            return sftpResponse;
        } catch (SftpException e) {
            sftpResponse = new SFTPResponse(false, e.getMessage());
            e.printStackTrace();

            return sftpResponse;
        } finally {
            sftpResponse = new SFTPResponse(true, null);

            sftp.disconnect();
            session.disconnect();
        }

        return sftpResponse;
    }

    public SFTPResponse uploadLogFile(String host, int port, String user, String password, String rut, Workbook wb) throws SFTPConnectionException {
        ChannelSftp sftp = new ChannelSftp();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");

        SFTPResponse sftpResponse = null;
        Session session = null;
        try {
            session = getConnectedSession(user, password, host, port);

            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            String firstFolder = rut.replace(".", "");
            sftp.cd(firstFolder);

            // Environment folder.
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            sftp.cd(environmentFolder);

            sftp.cd(RESULTS_FOLDER_NAME);

            OutputStream out = sftp.put("logs_" + dateFormat.format(new Date()) + ".xls");

            wb.write(out);

        } catch (JSchException e) {
            sftpResponse = new SFTPResponse(false, e.getMessage());
            e.printStackTrace();

            return sftpResponse;
        } catch (SftpException e) {
            sftpResponse = new SFTPResponse(false, e.getMessage());
            e.printStackTrace();

            return sftpResponse;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sftpResponse = new SFTPResponse(true, null);

            sftp.disconnect();
            session.disconnect();
        }

        return sftpResponse;
    }

    private Session settingSFTPParameters(Session session, String password, UserInfo ui, int timeout) throws JSchException {
        session.setPassword(password);
        session.setUserInfo(ui);
        session.setTimeout(timeout);

        return session;
    }

    public Session getSession(String user, String host, int port) throws SFTPConnectionException {
        Session session = null;
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(user, host, port);

            return session;
        } catch (JSchException e) {
            throw new SFTPConnectionException("No hay conexión con el servidor SFTP.", e);
        }
    }

    public Session getConnectedSession(String user, String password, String host, int port) throws SFTPConnectionException {
        Session session = null;
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(user, host, port);

            UserInfo ui = new SUserInfo(password, null);
            session = settingSFTPParameters(session, password, ui, SFTP_TIMEOUT);

            session.connect();
        } catch (JSchException e) {
            throw new SFTPConnectionException("No hay conexión con el servidor SFTP.", e);
        }

        return session;
    }

    /**
     * This method return a list of .csv files, from @rut/EXCEL/ folder in SFTP, connecting with process sftp parameters.
     * @param rut
     * @param process
     * @return list of .csv in rut/excel folder.
     */
    public List<String> listCSVFilesByRut(String rut, Process process) throws SFTPConnectionException {
        List<String> returnList = new ArrayList<String>();
        Session session = null;
        JSch jsch = new JSch();
        ChannelSftp sftp = new ChannelSftp();

        try {
            session = getConnectedSession(process.getUser(), process.getPassword(), process.getHost(), Integer.valueOf(process.getPort()));
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            // Enter to rut folder
            sftp.cd(rut);
            // Environment folder.
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            sftp.cd(environmentFolder);
            // Enter to excel folder
            sftp.cd(EXCEL_FOLDER_NAME);
            //sftp list files
            Vector<ChannelSftp.LsEntry> list = sftp.ls("*.csv");
            for(ChannelSftp.LsEntry entry : list) {
                returnList.add(entry.getFilename());
            }

            return returnList;

        } catch (JSchException e) {
            throw new SFTPConnectionException("Hubo un error al conectar al SFTP", e);
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if(sftp!=null)
                sftp.disconnect();
            if(session != null)
                session.disconnect();
        }

        return null;
    }

    public byte[] getCSVFile(Process process, String currentExcelFile) throws SFTPConnectionException {
        List<String> returnList = new ArrayList<String>();
        Session session = null;
        JSch jsch = new JSch();
        ChannelSftp sftp = new ChannelSftp();
        InputStream stream = null;

        try {
            session = getConnectedSession(process.getUser(), process.getPassword(), process.getHost(), Integer.valueOf(process.getPort()));
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            // Enter to rut folder
            sftp.cd(process.getBrokerRut().replace(".", ""));
            // Environment folder.
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            sftp.cd(environmentFolder);
            // Enter to excel folder
            sftp.cd(EXCEL_FOLDER_NAME);

            stream = sftp.get(currentExcelFile);
            byte[] bytes = IOUtils.toByteArray(stream);

            stream.close();
            sftp.rename(currentExcelFile, "Procesando_" + currentExcelFile);

            return bytes;
        } catch (JSchException e) {
            throw new SFTPConnectionException("Hubo un error al conectar al SFTP", e);
        } catch (SftpException e) {
            throw new SFTPConnectionException("Hubo un error al descargar el archivo del SFTP", e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sftp != null)
                sftp.disconnect();
            if (session != null)
                session.disconnect();
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public void renameFile(Process process, String fromFileName, String toFileNAme) throws SFTPConnectionException {
        Session session = null;
        ChannelSftp sftp = new ChannelSftp();
        InputStream stream = null;

        try {
            session = getConnectedSession(process.getUser(), process.getPassword(), process.getHost(), Integer.valueOf(process.getPort()));
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            // Enter to rut folder
            sftp.cd(process.getBrokerRut().replace(".", ""));
            // Environment folder.
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            sftp.cd(environmentFolder);
            // Enter to excel folder
            sftp.cd(EXCEL_FOLDER_NAME);

            sftp.rename(fromFileName, toFileNAme);
        } catch (JSchException e) {
            throw new SFTPConnectionException("Hubo un error al conectar al SFTP", e);
        } catch (SftpException e) {
            throw new SFTPConnectionException("Hubo un error al descargar el archivo del SFTP", e);
        }  finally {
            if (sftp != null)
                sftp.disconnect();
            if (session != null)
                session.disconnect();
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void renamePDFFile(Process process, String fromFileName, String toFileNAme) throws SFTPConnectionException {
        Session session = null;
        ChannelSftp sftp = new ChannelSftp();
        InputStream stream = null;

        try {
            session = getConnectedSession(process.getUser(), process.getPassword(), process.getHost(), Integer.valueOf(process.getPort()));
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            // Enter to rut folder
            sftp.cd(process.getBrokerRut().replace(".", ""));
            // Environment folder.
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            sftp.cd(environmentFolder);
            // Enter to excel folder
            sftp.cd(PDF_FOLDER_NAME);

            sftp.rename(fromFileName, toFileNAme);
        } catch (JSchException e) {
            throw new SFTPConnectionException("Hubo un error al conectar al SFTP", e);
        } catch (SftpException e) {
            throw new SFTPConnectionException("Hubo un error al descargar el archivo del SFTP", e);
        }  finally {
            if (sftp != null)
                sftp.disconnect();
            if (session != null)
                session.disconnect();
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public byte[] getPDFFile(Process process, String fileName) throws SFTPConnectionException {
        Session session = null;
        ChannelSftp sftp = new ChannelSftp();
        InputStream stream = null;

        try {
            session = getConnectedSession(process.getUser(), process.getPassword(), process.getHost(), Integer.valueOf(process.getPort()));
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            // Enter to rut folder
            sftp.cd(process.getBrokerRut().replace(".", ""));
            // Environment folder.
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            sftp.cd(environmentFolder);
            // Enter to excel folder
            sftp.cd(PDF_FOLDER_NAME);

            stream = sftp.get(fileName);
            byte[] bytes = IOUtils.toByteArray(stream);

            stream.close();

            return bytes;
        } catch (JSchException e) {
            throw new SFTPConnectionException("Hubo un error al conectar al SFTP", e);
        } catch (SftpException e) {
            throw new SFTPConnectionException("Hubo un error al descargar el archivo del SFTP", e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sftp != null)
                sftp.disconnect();
            if (session != null)
                session.disconnect();
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public boolean existPDFFile(Process process, String pdfPath) throws SFTPConnectionException {
        Session session = null;
        ChannelSftp sftp = new ChannelSftp();
        InputStream stream = null;

        try {
            session = getConnectedSession(process.getUser(), process.getPassword(), process.getHost(), Integer.valueOf(process.getPort()));
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            // Enter to rut folder
            sftp.cd(process.getBrokerRut().replace(".", ""));

            // Enter to environment folder
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            sftp.cd(environmentFolder);

            // Enter to excel folder
            sftp.cd(PDF_FOLDER_NAME);

            return exists(sftp, pdfPath);
        } catch (JSchException e) {
            throw new SFTPConnectionException("Hubo un error al conectar al SFTP", e);
        } catch (SftpException e) {
            throw new SFTPConnectionException("Hubo un error al descargar el archivo del SFTP", e);
        } finally {
            if (sftp != null)
                sftp.disconnect();
            if (session != null)
                session.disconnect();
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean exists(ChannelSftp channelSftp, String path) {
        Vector res = null;
        try {
            res = channelSftp.ls(path);
        } catch (SftpException e) {
            if (e.id == SSH_FX_NO_SUCH_FILE) {
                return false;
            }
        }
        return res != null;
    }

    public void checkFolders(String rut, Process process) throws SFTPConnectionException {
        Session session = null;
        ChannelSftp sftp = new ChannelSftp();
        InputStream stream = null;

        try {
            session = getConnectedSession(process.getUser(), process.getPassword(), process.getHost(), Integer.valueOf(process.getPort()));
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect(SFTP_TIMEOUT);

            // ¡Exists rut folder?
            boolean existsRutFolder = exists(sftp, rut);
            if(!existsRutFolder) sftp.mkdir(rut);
            sftp.cd(rut);

            // ¡Exists environment folder?
            String environmentFolder = configurationManager.getByKey(ABMConfiguration.EXECUTION_ENVIRONMENT_FOLDER).getValor();
            boolean existsEnvironmentFolder = exists(sftp, environmentFolder);
            if(!existsEnvironmentFolder) sftp.mkdir(environmentFolder);
            sftp.cd(environmentFolder);

            // ¡Exists EXCEL folder?
            boolean existsExcelFolder = exists(sftp, EXCEL_FOLDER_NAME);
            if(!existsExcelFolder) sftp.mkdir(EXCEL_FOLDER_NAME);

            // ¡Exists PDF folder?
            boolean existsPdfFolder = exists(sftp, PDF_FOLDER_NAME);
            if(!existsPdfFolder) sftp.mkdir(PDF_FOLDER_NAME);

            // ¡Exists RESULTADOS folder?
            boolean existsResultsFolder = exists(sftp, RESULTS_FOLDER_NAME);
            if(!existsResultsFolder) sftp.mkdir(RESULTS_FOLDER_NAME);
        } catch (JSchException e) {
            throw new SFTPConnectionException("Hubo un error al conectar al SFTP", e);
        } catch (SftpException e) {
            throw new SFTPConnectionException("Hubo un error al descargar el archivo del SFTP", e);
        } finally {
            if (sftp != null)
                sftp.disconnect();
            if (session != null)
                session.disconnect();
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}