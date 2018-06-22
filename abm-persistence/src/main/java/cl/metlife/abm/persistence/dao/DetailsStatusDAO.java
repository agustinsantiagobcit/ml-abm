package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.Detail;
import cl.metlife.abm.domain.DetailsStatus;

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
public class DetailsStatusDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public DetailsStatus getById(long id) {

        try {
            DetailsStatus group = em.find(DetailsStatus.class,id);
            em.detach(group);
            return group;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<DetailsStatus> findAll() {
        Query query = em.createQuery("select i from DetailsStatus i");

        List<DetailsStatus> resultList = query.getResultList();

        for (DetailsStatus detailStatus : resultList) {
            em.detach(detailStatus);
        }

        List<DetailsStatus> returnList = new ArrayList<DetailsStatus>();
        returnList.addAll(resultList);

        return returnList;
    }

}