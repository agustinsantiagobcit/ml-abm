package cl.metlife.abm.business.managers;

import cl.metlife.abm.domain.Broker;
import cl.metlife.abm.domain.Lot;
import cl.metlife.abm.persistence.dao.LotDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class LotManager {

    @EJB
    LotDAO lotDAO;

    public Lot getById(Long id) {
        return lotDAO.getById(id);
    }

    public List<Lot> findAll() {
        return lotDAO.findAll();
    }

    public List<Lot> findByLotStatus(Long lotStatusId){
        return lotDAO.findByLotStatus(lotStatusId);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Lot create(Lot lot) {
        return lotDAO.create(lot);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Lot update(Lot lot) {
        return lotDAO.update(lot);
    }

    public boolean delete(Lot lot) {
        return lotDAO.delete(lot);
    }

    public List<Lot> findByRutAndLotNumber(String rut, String aLong) {
        return lotDAO.findByRutAndLotNumber(rut, aLong);
    }
}