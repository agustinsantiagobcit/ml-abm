package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.Timer;

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
public class TimerDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public Timer getById(long id) {

        try {
            Timer timer = em.find(Timer.class,id);
            em.detach(timer);
            return timer;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<Timer> findAll() {
        Query query = em.createQuery("select i from Timer i");

        List<Timer> resultList = query.getResultList();

        for (Timer timer : resultList) {
            em.detach(timer);
        }

        List<Timer> returnList = new ArrayList<Timer>();
        returnList.addAll(resultList);

        return returnList;
    }

    public Timer create(Timer timer) {
        if ( timer == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        em.persist(timer);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        em.detach(timer);
        return timer;
    }

    public Timer update(Timer timer) {

        if ( timer == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Timer updated = em.merge(timer);
        em.flush();

        em.detach(updated);

        return updated;
    }

    public boolean delete(Timer timer) {

        if ( timer == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Timer toDelete = em.find(Timer.class,timer.getId());
        
        if(toDelete==null)
            return false;
        
        em.remove(toDelete);

        return true;
    }

}