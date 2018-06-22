package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.AltaCarga;
import cl.metlife.abm.domain.AltaTitular;
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
public class AltaCargaDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;


    public List<AltaCarga> findAll() {
        Query query = em.createQuery("select i from AltaCarga i");

        List<AltaCarga> resultList = query.getResultList();

        for (AltaCarga altaCarga : resultList) {
            em.detach(altaCarga);
        }

        List<AltaCarga> returnList = new ArrayList<AltaCarga>();
        returnList.addAll(resultList);

        return returnList;
    }

    public List<AltaCarga> findPipesponsorFileByBrokerRut(String brokerRut) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from AltaCarga i where i.rutCorredor =: brokerRut and i.procesado is null ").setParameter("brokerRut", brokerRut);

            List<AltaCarga> resultList = query.getResultList();

            for (AltaCarga altaCarga : resultList) {
                em.detach(altaCarga);
            }

            List<AltaCarga> returnList = new ArrayList<AltaCarga>();
            returnList.addAll(resultList);

            return returnList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }


    public void update(AltaCarga altaCarga) {
        if ( altaCarga == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        AltaCarga updated = em.merge(altaCarga);
        em.flush();
    }

    public List<AltaCarga> findPipesponsorFileByLotId(Long lotId) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from AltaCarga i where i.lotId =: lotId and i.procesado is null ").setParameter("lotId", lotId);

            List<AltaCarga> resultList = query.getResultList();

            for (AltaCarga altaCarga : resultList) {
                em.detach(altaCarga);
            }

            List<AltaCarga> returnList = new ArrayList<AltaCarga>();
            returnList.addAll(resultList);

            return returnList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }

    public int updateStatus(Long lotId, String status) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("update AltaCarga at set at.procesado = :status where at.lotId=:lotId").setParameter("lotId", lotId).setParameter("status", status);

            int response = query.executeUpdate();

            return response;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al updatear status de la data insertada de PipeSponsor.");
        }
    }

    public List<AltaCarga> findAltaCarByBrokerRutAndLotNumber(String brokerRut, Long lotNumber) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from AltaCarga i where i.rutCorredor =:brokerRut and i.lote =:lotNumber ")
                    .setParameter("brokerRut", brokerRut).setParameter("lotNumber", lotNumber);

            List<AltaCarga> resultList = query.getResultList();

            return resultList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }
}