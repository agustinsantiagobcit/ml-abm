package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.Detail;

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
public class DetailDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public Detail getById(long id) {

        try {
            Detail group = em.find(Detail.class,id);
            em.detach(group);
            return group;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<Detail> findAll() {
        Query query = em.createQuery("select i from Detail i");

        List<Detail> resultList = query.getResultList();

        for (Detail detail : resultList) {
            em.detach(detail);
        }

        List<Detail> returnList = new ArrayList<Detail>();
        returnList.addAll(resultList);

        return returnList;
    }

    public Detail create(Detail detail) {
        if (detail == null)
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        em.persist(detail);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        em.detach(detail);
        return detail;
    }

    public Detail update(Detail detail) {
        if (detail == null)
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Detail updated = em.merge(detail);

        em.flush();
        em.detach(updated);

        return updated;
    }

    public boolean delete(Detail detail) {
        if (detail == null)
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Detail toDelete = em.find(Detail.class,detail.getId());
        if(toDelete==null)
            return false;
        
        em.remove(toDelete);

        return true;
    }

    public List<Detail> findByLotId(Long lotId) {
        Query query = em.createQuery("select i from Detail i where i.lotId =:lotId").setParameter("lotId", lotId);

        List<Detail> resultList = query.getResultList();

        for (Detail detail : resultList) {
            em.detach(detail);
        }

        List<Detail> returnList = new ArrayList<Detail>();
        returnList.addAll(resultList);

        return returnList;
    }

    public List<Detail> findByLotId(Long lotId, Long detailStatusId) {
        Query query = em.createQuery("select i from Detail i where i.lotId =:lotId and i.statusId =:detailStatusId").setParameter("lotId", lotId).setParameter("detailStatusId", detailStatusId);

        List<Detail> resultList = query.getResultList();

        for (Detail detail : resultList) {
            em.detach(detail);
        }

        List<Detail> returnList = new ArrayList<Detail>();
        returnList.addAll(resultList);

        return returnList;
    }
}