package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.LotStatus;

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
public class LotStatusDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public LotStatus getById(long id) {

        try {
            LotStatus group = em.find(LotStatus.class,id);
            em.detach(group);
            return group;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<LotStatus> findAll() {
        Query query = em.createQuery("select i from LotStatus i");

        List<LotStatus> resultList = query.getResultList();

        for (LotStatus detailStatus : resultList) {
            em.detach(detailStatus);
        }

        List<LotStatus> returnList = new ArrayList<LotStatus>();
        returnList.addAll(resultList);

        return returnList;
    }

}