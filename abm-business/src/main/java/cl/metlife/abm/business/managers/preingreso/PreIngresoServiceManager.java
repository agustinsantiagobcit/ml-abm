package cl.metlife.abm.business.managers.preingreso;

import cl.metlife.abm.business.managers.ABMConfigurationManager;
import cl.metlife.ws.clients.pipesponsor.ISponsorMouleService;
import cl.metlife.ws.clients.pipesponsor.SponsorModuleService;
import cl.metlife.ws.clients.preingreso.bulk.WsBulk;
import cl.metlife.ws.clients.preingreso.bulk.WsBulkSoap;
import cl.metlife.ws.clients.preingreso.session.SesionBo;
import cl.metlife.ws.clients.preingreso.session.WsSesion;
import cl.metlife.ws.clients.preingreso.session.WsSesionSoap;

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
public class PreIngresoServiceManager {

    private final String WSDL_CONFIG_KEY_SESSION = "preingreso.session.service.wsdl";
    private final String ENDPOINT_CONFIG_KEY_SESSION = "preingreso.session.service.endpoint";
    private final String WSDL_CONFIG_KEY_BULK = "preingreso.bulk.service.wsdl";
    private final String ENDPOINT_CONFIG_KEY_BULK = "preingreso.bulk.service.endpoint";
    private final String PREINGRESO_USER = "preingreso.service.username";
    private final String PREINGRESO_PASS = "preingreso.service.password";

    @EJB
    ABMConfigurationManager configurationManager;

    private WsBulkSoap wsBulkSoap;

    public String getSesionId(){
        String wsdl_url_session = configurationManager.getByKey(WSDL_CONFIG_KEY_SESSION).getValor();
        String endpoint_url_session = configurationManager.getByKey(ENDPOINT_CONFIG_KEY_SESSION).getValor();
        String user = configurationManager.getByKey(PREINGRESO_USER).getValor();
        String pass = configurationManager.getByKey(PREINGRESO_PASS).getValor();

        WsSesionSoap session = null;
        WsBulkSoap wsBulk = null;
        try {
            session = new WsSesion(new URL(wsdl_url_session),  new QName("http://preingreso.metlife.cl/", "WsSesion")).getWsSesionSoap12();
            setEndpointAddress(session, endpoint_url_session);

            SesionBo sesionBo = session.logon(user, pass);
            return sesionBo.getId();

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error con URL", e);
        }
    }

    public WsBulkSoap getBulkService(String id){
        if (wsBulkSoap == null) {
            String wsdl_url_bulk = configurationManager.getByKey(WSDL_CONFIG_KEY_BULK).getValor();
            String endpoint_url_bulk = configurationManager.getByKey(ENDPOINT_CONFIG_KEY_BULK).getValor();

            WsBulkSoap wsBulk = null;
            try {
                wsBulk = new WsBulk(new URL(wsdl_url_bulk),  new QName("http://preingreso.metlife.cl/", "WsBulk")).getWsBulkSoap12();
                setEndpointAddress(wsBulk, endpoint_url_bulk);

                this.wsBulkSoap = wsBulk;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error con URL", e);
            }
        }

        return wsBulkSoap;
    }

    private void setEndpointAddress(Object port, String newAddress) {
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, newAddress);
    }

}
