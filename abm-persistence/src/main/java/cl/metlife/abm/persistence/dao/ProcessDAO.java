package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.Process;

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
public class ProcessDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public Process getById(long id) {

        try {
            Process process = em.find(Process.class,id);
            em.detach(process);
            return process;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<Process> findAll() {
        Query query = em.createQuery("select i from Process i");

        List<Process> resultList = query.getResultList();

        for (Process process : resultList) {
            em.detach(process);
        }

        List<Process> returnList = new ArrayList<Process>();
        returnList.addAll(resultList);

        return returnList;
    }

    public Process create(Process process) {
        if ( process == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        em.persist(process);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        em.detach(process);
        return process;
    }

    public Process update(Process process) {

        if ( process == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Process updated = em.merge(process);
        em.flush();

        em.detach(updated);

        return updated;
    }

    public boolean delete(Process process) {

        if ( process == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Process toDelete = em.find(Process.class,process.getId());
        
        if(toDelete==null)
            return false;
        
        em.remove(toDelete);

        return true;
    }

    public Process getByMultiBrokerRut(String multiBrokerRut) {
        Process obj = null;

        try {
            Query query = em.createQuery("select i from Process i where i.brokerRut =:multiBrokerRut", Process.class).setParameter("multiBrokerRut", multiBrokerRut);

            obj = (Process) query.getSingleResult();
        } catch (Exception e){
            return null;
        }

        return obj;
    }

    public List<Process> findActiveProcesses() {
        Query query = em.createQuery("select i from Process i where i.active =:active").setParameter("active", true);

        List<Process> resultList = query.getResultList();

        for (Process process : resultList) {
            em.detach(process);
        }

        List<Process> returnList = new ArrayList<Process>();
        returnList.addAll(resultList);

        return returnList;
    }

    public List<String> getAllRegisteredRuts(){
        return em.createQuery("select DISTINCT(i.brokerRut) from Process i where i.brokerRut is not null order by i.brokerRut asc").getResultList();
    }
}