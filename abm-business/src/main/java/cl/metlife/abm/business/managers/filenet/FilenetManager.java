package cl.metlife.abm.business.managers.filenet;

import cl.metlife.abm.business.managers.ABMConfigurationManager;
import cl.metlife.abm.business.managers.LogbookManager;
import cl.metlife.abm.business.managers.filenet.exception.EncodeException;
import cl.metlife.abm.business.managers.sftp.SFTPManager;
import cl.metlife.abm.domain.*;
import cl.metlife.ws.clients.filenet.Document;
import cl.metlife.ws.clients.filenet.Metadata;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by BluePrints Developer on 13-02-2017.
 */
@Singleton
public class FilenetManager {
    static final Logger logger = LoggerFactory.getLogger(FilenetManager.class);

    @EJB
    FilenetServiceManager fileNetServiceManager;

    @EJB
    ABMConfigurationManager abmConfigurationManager;

    @EJB
    LogbookManager logbookManager;

    @EJB
    SFTPManager sftpManager;


    public String uploadFile(Lot lot, String fileName, byte[] fileArray, Detail detail) throws SQLException, EncodeException {
        String file = fileName;
        String codigoFileNet = null;
        String rut = lot.getProcess().getBrokerRut().replace("-", "").replace(".", "");

        String encodeFile = encodeFile(fileArray);

        Document document = new Document();
        document.setFilename(file);
        document.setSerializedFile(encodeFile);

        codigoFileNet = fileNetServiceManager.getService().putDocument(getConfigByKey(ABMConfiguration.FILENET_APPLICATIONID),
                getConfigByKey(ABMConfiguration.FILENET_CREATORTASKID), rut, document);
        logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_INFO, Log.LOG_STEP_FILENET,
                "Se cargó correctamente el PDF " + file + " a Filenet. [CODE: " + codigoFileNet + "]", lot, detail, null, null);

        return codigoFileNet;
    }

    public String searchDocuments(String fileName, String bussinessDocumentId, String creatorTaskId, String applicationId) throws SQLException, EncodeException {
        List<Metadata> metadataArrayList = new ArrayList<Metadata>();
        Metadata metadata = new Metadata();
        metadata.setName("DocumentTitle");metadata.setValue(fileName);
        metadataArrayList.add(metadata);

        Metadata metadata1 = new Metadata();
        metadata1.setName("businessDocumentId");metadata1.setValue(bussinessDocumentId);
        metadataArrayList.add(metadata1);

        Metadata metadata2 = new Metadata();
        metadata2.setName("creatorTaskId");metadata2.setValue(creatorTaskId);
        metadataArrayList.add(metadata2);

        List<String> stringList = fileNetServiceManager.getService().searchDocuments(applicationId,metadataArrayList);

        if(stringList == null || stringList.size() == 0)
            return null;
        else
            return stringList.get(stringList.size() - 1);
    }

    private String encodeFile(byte[] fileArray) throws EncodeException {
        try {
            Base64 base64 = new Base64();

            InputStream inputStream = null;
            String encodedFile = "";

            encodedFile = base64.encodeToString(fileArray);

            return encodedFile;
        } catch (Exception e){
            throw new EncodeException("Hubo un error al conectar al SFTP", e);
        }

    }

    private List<Metadata> makeServiceInput(String fileName, String rut) {
        List<Metadata> metadataArrayList = new ArrayList<Metadata>();
        new ArrayList<Metadata>();
        Metadata metadata = new Metadata();
        metadata.setName("DocumentTitle");
        metadata.setValue(fileName);
        metadataArrayList.add(metadata);

        Metadata metadata1 = new Metadata();
        metadata1.setName("businessDocumentId");
        metadata1.setValue(rut);
        metadataArrayList.add(metadata1);

        Metadata metadata2 = new Metadata();
        metadata2.setName("creatorTaskId");
        metadata2.setValue(getConfigByKey(ABMConfiguration.FILENET_CREATORTASKID));
        metadataArrayList.add(metadata2);

        return metadataArrayList;
    }

    private String getConfigByKey(String key) {
        return abmConfigurationManager.getByKey(key).getValor();
    }

}