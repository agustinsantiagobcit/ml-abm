package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.ExclusionCarga;
import cl.metlife.abm.persistence.dao.exception.PipeSponsorDBException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class ExclusionCargaDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;


    public List<ExclusionCarga> findAll() {
        Query query = em.createQuery("select i from ExclusionCarga i");

        List<ExclusionCarga> resultList = query.getResultList();

        for (ExclusionCarga exclusionCarga : resultList) {
            em.detach(exclusionCarga);
        }

        List<ExclusionCarga> returnList = new ArrayList<ExclusionCarga>();
        returnList.addAll(resultList);

        return returnList;
    }

    public List<ExclusionCarga> findPipesponsorFileByBrokerRut(String brokerRut) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from ExclusionCarga i where i.rutCorredor =: brokerRut and i.procesado is null ").setParameter("brokerRut", brokerRut);

            List<ExclusionCarga> resultList = query.getResultList();

            for (ExclusionCarga exclusionCarga : resultList) {
                em.detach(exclusionCarga);
            }

            List<ExclusionCarga> returnList = new ArrayList<ExclusionCarga>();
            returnList.addAll(resultList);

            return returnList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }


    public void update(ExclusionCarga exclusionCarga) {
        if ( exclusionCarga == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        ExclusionCarga updated = em.merge(exclusionCarga);
        em.flush();
    }

    public List<ExclusionCarga> findPipesponsorFileByLotId(Long lotId) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from ExclusionCarga i where i.lotId =: lotId and i.procesado is null ").setParameter("lotId", lotId);

            List<ExclusionCarga> resultList = query.getResultList();

            for (ExclusionCarga exclusionCarga : resultList) {
                em.detach(exclusionCarga);
            }

            List<ExclusionCarga> returnList = new ArrayList<ExclusionCarga>();
            returnList.addAll(resultList);

            return returnList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }

    public int updateStatus(Long lotId, String status) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("update ExclusionCarga at set at.procesado = :status where at.lotId=:lotId").setParameter("lotId", lotId).setParameter("status", status);

            int response = query.executeUpdate();

            return response;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al updatear status de la data insertada de PipeSponsor.");
        }
    }

    public List<ExclusionCarga> findExcCarByBrokerRutAndLotNumber(String brokerRut, Long lotNumber) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from ExclusionCarga i where i.rutCorredor =:brokerRut and i.lote =:lotNumber ")
                    .setParameter("brokerRut", brokerRut).setParameter("lotNumber", lotNumber);

            List<ExclusionCarga> resultList = query.getResultList();

            return resultList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }
}