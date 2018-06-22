package cl.metlife.abm.business.managers.filenet;

import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.metlife.abm.business.managers.ABMConfigurationManager;
import cl.metlife.ws.clients.filenet.ConectorFileNetWSI;
import cl.metlife.ws.clients.filenet.FileNetWS;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by BluePrints Developer on 13-02-2017.
 */
@Singleton
public class FilenetServiceManager {

    private final String WSDL_CONFIG_KEY = "filenet.service.wsdl";
    private final String ENDPOINT_CONFIG_KEY = "filenet.service.endpoint";

    @EJB
    ABMConfigurationManager configurationManager;

    private ConectorFileNetWSI fileNetWS;

    public ConectorFileNetWSI getService(){
        if (fileNetWS == null) {
            String wsdl_url = configurationManager.getByKey(WSDL_CONFIG_KEY).getValor();
            String endpoint_url = configurationManager.getByKey(ENDPOINT_CONFIG_KEY).getValor();

            FileNetWS service = null;
            try {
                service = new  FileNetWS(new URL(wsdl_url),  new QName("http://filenet.ws.metlife.cl/", "FileNetWS") );
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error con URL", e);
            }
            this.fileNetWS = service.getFilenet();
            setEndpointAddress(fileNetWS, endpoint_url);
        }
        return fileNetWS;
    }


    private void setEndpointAddress(Object port, String newAddress) {
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, newAddress);
    }

}
