package cl.metlife.abm.controller;

import cl.blueprintsit.framework.web.BaseBean;
import cl.metlife.abm.business.managers.ABMConfigurationManager;
import cl.metlife.abm.domain.ABMConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 4/25/2018.
 * SFTP Repositories Maintaner.
 */

@ManagedBean(name="abmConfigBean")
@ViewScoped
public class ConfigurationsBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    static final Logger logger = LoggerFactory.getLogger(ConfigurationsBean.class);

    /* Table List */
    public List<ABMConfiguration> ABMConfigurations;
    private ABMConfiguration editItem;

    /* Persistence Objects */
    @EJB
    private ABMConfigurationManager abmConfigurationManager;


    @PostConstruct
    public void init(){

    }

    public List<ABMConfiguration> getAllConfigurations(){
        if (this.ABMConfigurations == null) {
            this.ABMConfigurations = new ArrayList<ABMConfiguration>();
            this.ABMConfigurations = abmConfigurationManager.findAll();
        }

        return this.ABMConfigurations;
    }

    public void delete(ABMConfiguration item) {

        if(abmConfigurationManager.delete(item)){
            resetList();
            super.showInfo("Aviso", "Se eliminó la configuración satisfactoriamente.");
        } else {
            super.showError("Error", "Hubo un error al eliminar la configuración.");
        }

        this.ABMConfigurations = abmConfigurationManager.findAll();
    }

    public ABMConfiguration getEditItem() {
        if(this.editItem == null){
            this.editItem = new ABMConfiguration();
        }

        return editItem;
    }

    public void setEditItem(ABMConfiguration item) {
        this.editItem = item;
    }

    private void resetList() {
        this.ABMConfigurations = null;
    }

    public void newRepository(){
        this.editItem = new ABMConfiguration();
    }

    public void save() {
        if ( this.editItem.getId() == null) {
            this.abmConfigurationManager.create(this.editItem);
        } else {
            this.abmConfigurationManager.update(this.editItem);
        }

        resetList();
        newRepository();
        this.ABMConfigurations = abmConfigurationManager.findAll();
        showInfo("Aviso", "Se guardó correctamente la configuración SFTP.");
    }

}

