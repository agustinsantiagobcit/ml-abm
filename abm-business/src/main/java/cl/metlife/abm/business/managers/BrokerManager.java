package cl.metlife.abm.business.managers;

import cl.metlife.abm.domain.Broker;
import cl.metlife.abm.persistence.dao.BrokerDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class BrokerManager {

    @EJB
    BrokerDAO brokerDAO;

    public Broker getById(Long id) {
        return brokerDAO.getById(id);
    }

    public List<Broker> findAll() {
        return brokerDAO.findAll();
    }

    public Broker create(Broker corredor) {
        return brokerDAO.create(corredor);
    }

    public Broker update(Broker corredor) {
        return brokerDAO.update(corredor);
    }

    public boolean delete(Broker corredor) {
        return brokerDAO.delete(corredor);
    }

}