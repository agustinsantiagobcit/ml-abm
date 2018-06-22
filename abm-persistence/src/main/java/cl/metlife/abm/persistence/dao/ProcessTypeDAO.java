package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.ProcessType;

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
public class ProcessTypeDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public ProcessType getById(long id) {

        try {
            ProcessType group = em.find(ProcessType.class,id);
            em.detach(group);
            return group;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<ProcessType> findAll() {
        Query query = em.createQuery("select i from ProcessType i");

        List<ProcessType> resultList = query.getResultList();

        for (ProcessType detailStatus : resultList) {
            em.detach(detailStatus);
        }

        List<ProcessType> returnList = new ArrayList<ProcessType>();
        returnList.addAll(resultList);

        return returnList;
    }

}