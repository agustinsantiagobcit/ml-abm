package cl.metlife.abm.business.managers.pipesponsor;

import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.metlife.abm.business.managers.ABMConfigurationManager;
import cl.metlife.ws.clients.filenet.ConectorFileNetWSI;
import cl.metlife.ws.clients.filenet.FileNetWS;
import cl.metlife.ws.clients.pipesponsor.ISponsorMouleService;
import cl.metlife.ws.clients.pipesponsor.LogProcessDTO;
import cl.metlife.ws.clients.pipesponsor.SponsorModuleService;

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
public class PipeSponsorServiceManager {

    private final String WSDL_CONFIG_KEY = "pipesponsor.service.wsdl";
    private final String ENDPOINT_CONFIG_KEY = "pipesponsor.service.endpoint";

    @EJB
    ABMConfigurationManager configurationManager;

    private ISponsorMouleService pipeWS;

    public ISponsorMouleService getService(){
        if (pipeWS == null) {
            String wsdl_url = configurationManager.getByKey(WSDL_CONFIG_KEY).getValor();
            String endpoint_url = configurationManager.getByKey(ENDPOINT_CONFIG_KEY).getValor();

            SponsorModuleService service = null;
            try {
                service = new SponsorModuleService(new URL(wsdl_url),  new QName("http://tempuri.org/", "SponsorModuleService"));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error con URL", e);
            }
            this.pipeWS = service.getBasicHttpBindingISponsorMouleService();
            setEndpointAddress(pipeWS, endpoint_url);
        }
        return pipeWS;
    }


    private void setEndpointAddress(Object port, String newAddress) {
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, newAddress);
    }

}
