package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.ExclusionTitular;
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
public class ExclusionTitularDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;


    public List<ExclusionTitular> findAll() {
        Query query = em.createQuery("select i from ExclusionTitular i");

        List<ExclusionTitular> resultList = query.getResultList();

        for (ExclusionTitular exclusionTitular : resultList) {
            em.detach(exclusionTitular);
        }

        List<ExclusionTitular> returnList = new ArrayList<ExclusionTitular>();
        returnList.addAll(resultList);

        return returnList;
    }

    public List<ExclusionTitular> findPipesponsorFileByBrokerRut(String brokerRut) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from ExclusionTitular i where i.rutCorredor =: brokerRut and i.procesado is null ").setParameter("brokerRut", brokerRut);

            List<ExclusionTitular> resultList = query.getResultList();

            for (ExclusionTitular exclusionTitular : resultList) {
                em.detach(exclusionTitular);
            }

            List<ExclusionTitular> returnList = new ArrayList<ExclusionTitular>();
            returnList.addAll(resultList);

            return returnList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }


    public void update(ExclusionTitular exclusionTitular) {
        if ( exclusionTitular == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        ExclusionTitular updated = em.merge(exclusionTitular);
        em.flush();
    }

    public List<ExclusionTitular> findPipesponsorFileByLotId(Long lotId) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from ExclusionTitular i where i.lotId =: lotId and i.procesado is null ").setParameter("lotId", lotId);

            List<ExclusionTitular> resultList = query.getResultList();

            for (ExclusionTitular exclusionTitular : resultList) {
                em.detach(exclusionTitular);
            }

            List<ExclusionTitular> returnList = new ArrayList<ExclusionTitular>();
            returnList.addAll(resultList);

            return returnList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }

    public int updateStatus(Long lotId, String status) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("update ExclusionTitular at set at.procesado = :status where at.lotId=:lotId").setParameter("lotId", lotId).setParameter("status", status);

            int response = query.executeUpdate();

            return response;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al updatear status de la data insertada de PipeSponsor.");
        }
    }

    public List<ExclusionTitular> findExcTitByBrokerRutAndLotNumber(String brokerRut, Long lotNumber) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from ExclusionTitular i where i.rutCorredor =:brokerRut and i.lote =:lotNumber ")
                    .setParameter("brokerRut", brokerRut).setParameter("lotNumber", lotNumber);

            List<ExclusionTitular> resultList = query.getResultList();

            return resultList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }
}