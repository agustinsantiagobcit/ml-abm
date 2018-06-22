package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.ABMConfiguration;

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
public class ABMConfigurationDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public ABMConfiguration getById(long id) {

        try {
            ABMConfiguration group = em.find(ABMConfiguration.class,id);
            em.detach(group);
            return group;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<ABMConfiguration> findAll() {
        Query query = em.createQuery("select i from ABMConfiguration i");

        List<ABMConfiguration> resultList = query.getResultList();

        for (ABMConfiguration ABMConfiguration : resultList) {
            em.detach(ABMConfiguration);
        }

        List<ABMConfiguration> returnList = new ArrayList<ABMConfiguration>();
        returnList.addAll(resultList);

        return returnList;
    }

    public ABMConfiguration create(ABMConfiguration ABMConfiguration) {
        if ( ABMConfiguration == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        em.persist(ABMConfiguration);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        em.detach(ABMConfiguration);
        return ABMConfiguration;
    }

    public ABMConfiguration update(ABMConfiguration ABMConfiguration) {

        if ( ABMConfiguration == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        ABMConfiguration updated = em.merge(ABMConfiguration);
        em.flush();

        em.detach(updated);

        return updated;
    }

    public boolean delete(ABMConfiguration ABMConfiguration) {

        if ( ABMConfiguration == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        ABMConfiguration toDelete = em.find(ABMConfiguration.class, ABMConfiguration.getId());
        
        if(toDelete==null)
            return false;
        
        em.remove(toDelete);

        return true;
    }

    public ABMConfiguration getByKey(String key) {
        Query query = em.createQuery("select i from ABMConfiguration i where i.name=:key", ABMConfiguration.class).setParameter("key", key);

        ABMConfiguration result = (ABMConfiguration) query.getSingleResult();

        return result;
    }
}