package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.Lot;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class LotDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public Lot getById(long id) {

        try {
            Lot lot = em.find(Lot.class,id);
            em.detach(lot);
            return lot;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<Lot> findAll() {
        Query query = em.createQuery("select i from Lot i");

        List<Lot> resultList = query.getResultList();

        for (Lot lot : resultList) {
            em.detach(lot);
        }

        List<Lot> returnList = new ArrayList<Lot>();
        returnList.addAll(resultList);

        return returnList;
    }

    public Lot create(Lot lot) {
        if ( lot == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        em.persist(lot);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        em.detach(lot);
        return lot;
    }

    public Lot update(Lot lot) {

        if ( lot == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Lot updated = em.merge(lot);
        em.flush();

        em.detach(updated);

        return updated;
    }

    public boolean delete(Lot lot) {

        if ( lot == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Lot toDelete = em.find(Lot.class,lot.getId());
        
        if(toDelete==null)
            return false;
        
        em.remove(toDelete);

        return true;
    }

    public List<Lot> findByLotStatus(Long lotStatusId) {
        Query query = em.createQuery("select i from Lot i where i.lotStatusId =:lotStatusId order by i.id asc").setParameter("lotStatusId", lotStatusId);

        List<Lot> resultList = query.getResultList();

        for (Lot lot : resultList) {
            em.detach(lot);
        }

        List<Lot> returnList = new ArrayList<Lot>();
        returnList.addAll(resultList);

        return returnList;
    }

    public List<Lot> findByRutAndLotNumber(String rut, String number) {
        Query query = em.createQuery("select i from Lot i where i.process.brokerRut =:rut and i.number =:number order by i.id desc").setParameter("rut", rut).setParameter("number", number);

        List<Lot> resultList = query.getResultList();

        for (Lot lot : resultList) {
            em.detach(lot);
        }

        List<Lot> returnList = new ArrayList<Lot>();
        returnList.addAll(resultList);

        return returnList;
    }
}