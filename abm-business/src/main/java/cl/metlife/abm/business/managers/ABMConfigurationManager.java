package cl.metlife.abm.business.managers;

import cl.metlife.abm.domain.ABMConfiguration;
import cl.metlife.abm.persistence.dao.ABMConfigurationDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class ABMConfigurationManager {

    @EJB
    ABMConfigurationDAO abmConfigurationDAO;

    public ABMConfiguration getById(Long id) {
        return abmConfigurationDAO.getById(id);
    }

    public ABMConfiguration getByKey(String key){
        return abmConfigurationDAO.getByKey(key);
    }

    public List<ABMConfiguration> findAll() {
        return abmConfigurationDAO.findAll();
    }

    public ABMConfiguration create(ABMConfiguration ABMConfiguration) {
        return abmConfigurationDAO.create(ABMConfiguration);
    }

    public ABMConfiguration update(ABMConfiguration ABMConfiguration) {
        return abmConfigurationDAO.update(ABMConfiguration);
    }

    public boolean delete(ABMConfiguration ABMConfiguration) {
        return abmConfigurationDAO.delete(ABMConfiguration);
    }

}