package cl.metlife.abm.persistence.dao;

import cl.blueprintsit.cl.utils.LazyModelUtils;
import cl.metlife.abm.domain.Log;
import cl.metlife.abm.domain.Log;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class LogDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public Log getById(long id) {

        try {
            Log log = em.find(Log.class,id);
            em.detach(log);
            return log;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<Log> findAll() {
        Query query = em.createQuery("select i from Log i");

        List<Log> resultList = query.getResultList();

        for (Log log : resultList) {
            em.detach(log);
        }

        List<Log> returnList = new ArrayList<Log>();
        returnList.addAll(resultList);

        return returnList;
    }

    public Log create(Log log) {
        if ( log == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        em.persist(log);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        em.detach(log);
        return log;
    }

    public Log update(Log log) {

        if ( log == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Log updated = em.merge(log);
        em.flush();

        em.detach(updated);

        return updated;
    }

    public boolean delete(Log log) {

        if ( log == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Log toDelete = em.find(Log.class,log.getId());
        
        if(toDelete==null)
            return false;
        
        em.remove(toDelete);

        return true;
    }

    public List<Log> findByProcess(Long processId) {
        Query query = em.createQuery("select i from Log i where i.lot.processId=:processId order by i.id desc")
                .setParameter("processId", processId);

        List<Log> resultList = query.getResultList();

        for (Log log : resultList) {
            em.detach(log);
        }

        List<Log> returnList = new ArrayList<Log>();
        returnList.addAll(resultList);

        return returnList;
    }

    public int countByProcess(Long processId) {
        Query query = em.createQuery("select count(i) from Log i where i.processId=:processId")
                .setParameter("processId", processId);

        int response = ((Number) query.getSingleResult()).intValue();

        return response;
    }

    public List<Log> findByLotId(Long lotId) {
        Query query = em.createQuery("select i from Log i where i.lotId=:lotId order by i.id desc")
                .setParameter("lotId", lotId);

        List<Log> resultList = query.getResultList();

        for (Log log : resultList) {
            em.detach(log);
        }

        List<Log> returnList = new ArrayList<Log>();
        returnList.addAll(resultList);

        return returnList;
    }

    public List<Log> findByProcessIdFromDateAndStep(Long processId, Date fromDate, String logStep) {
        Query query = em.createQuery("select i from Log i where i.lot.processId=:processId and i.step=:logStep" +
                " and i.date between :fromDate and :currentDate order by i.id desc").setParameter("processId", processId)
                .setParameter("logStep", logStep).setParameter("logStep", logStep).setParameter("fromDate", fromDate)
                .setParameter("currentDate", new Date());

        List<Log> resultList = query.getResultList();

        for (Log log : resultList) {
            em.detach(log);
        }

        List<Log> returnList = new ArrayList<Log>();
        returnList.addAll(resultList);

        return returnList;
    }

    public Long countFiltered(Map<String, Object> filters) throws ParseException {
        TypedQuery<Long> query = null;

        query = em.createQuery("SELECT count(l.id) FROM Log l  " + LazyModelUtils.buildWhereClauses(filters), Long.class);

        LazyModelUtils.setFilterParams(filters, query);
        return query.getSingleResult();
    }

    public List<Log> findFiltered(int first, int pageSize, String sortField,Boolean sortOrder,Map<String, Object> filters) throws ParseException{

        List<Log> response = new ArrayList<Log>();
        StringBuilder orderClause = new StringBuilder();
        String clause =  "";
        if ( sortField != null && !"".equals(sortField) )
            orderClause.append(" ORDER BY l." + sortField + (sortOrder != null && sortOrder ? " ASC" : " DESC"));
        else
            orderClause.append(" ORDER BY l.id DESC");


        TypedQuery<Log> query = null;
        query =  em.createQuery("select l from Log l " + LazyModelUtils.buildWhereClauses(filters) + orderClause.toString() , Log.class);

        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        LazyModelUtils.setFilterParams(filters, query);
        List<Log> res = query.getResultList();

        return res;
    }

    public List<Log> findByProcessAndStatus(Long processId, Long lotId, String logLevelError) {
        Query query = em.createQuery("select i from Log i where i.lot.processId=:processId and i.level =:logLevelError" +
                " and i.lotId =:lotId order by i.id desc").setParameter("processId", processId).setParameter("logLevelError", logLevelError)
                .setParameter("lotId", lotId);

        List<Log> resultList = query.getResultList();

        for (Log log : resultList) {
            em.detach(log);
        }

        List<Log> returnList = new ArrayList<Log>();
        returnList.addAll(resultList);

        return returnList;
    }
}