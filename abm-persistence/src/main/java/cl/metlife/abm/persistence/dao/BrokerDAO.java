package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.Broker;

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
public class BrokerDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public Broker getById(long id) {

        try {
            Broker group = em.find(Broker.class,id);
            em.detach(group);
            return group;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<Broker> findAll() {
        Query query = em.createQuery("select i from Broker i");

        List<Broker> resultList = query.getResultList();

        for (Broker group : resultList) {
            em.detach(group);
        }

        List<Broker> returnList = new ArrayList<Broker>();
        returnList.addAll(resultList);

        return returnList;
    }

    public Broker create(Broker group) {
        if ( group == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        em.persist(group);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        em.detach(group);
        return group;
    }

    public Broker update(Broker group) {

        if ( group == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Broker updated = em.merge(group);
        em.flush();

        em.detach(updated);

        return updated;
    }

    public boolean delete(Broker group) {

        if ( group == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Broker toDelete = em.find(Broker.class,group.getId());
        
        if(toDelete==null)
            return false;
        
        em.remove(toDelete);

        return true;
    }

}