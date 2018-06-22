package cl.blueprintsit.framework.web.configuration;

import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.blueprintsit.framework.domain.Configuration;
import cl.blueprintsit.framework.web.BaseBean;
import org.primefaces.event.RowEditEvent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 1/27/2016.
 */
@ManagedBean(name="configurationsBean")
@ViewScoped
public class ConfigurationsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ConfigurationManager manager;


    public List<Configuration> configurationList;

    public List<Configuration> getAllConfiguraciones(){
        if(configurationList == null)
            configurationList = manager.findAll();

        return configurationList;
    }

    public void onConfigEdit(RowEditEvent event) {
        Configuration configuration = (Configuration) event.getObject();
        manager.update(configuration);
    }

    public void onEditCancel(RowEditEvent event) {}

}
