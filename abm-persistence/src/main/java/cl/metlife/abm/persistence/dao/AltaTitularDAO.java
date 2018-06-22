package cl.metlife.abm.persistence.dao;

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
public class AltaTitularDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;


    public List<AltaTitular> findAll() {
        Query query = em.createQuery("select i from AltaTitular i");

        List<AltaTitular> resultList = query.getResultList();

        for (AltaTitular altaTitular : resultList) {
            em.detach(altaTitular);
        }

        List<AltaTitular> returnList = new ArrayList<AltaTitular>();
        returnList.addAll(resultList);

        return returnList;
    }

    public List<AltaTitular> findPipesponsorFileByBrokerRut(String brokerRut) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from AltaTitular i where i.rutCorredor =: brokerRut and i.procesado is null ").setParameter("brokerRut", brokerRut);

            List<AltaTitular> resultList = query.getResultList();

            for (AltaTitular altaTitular : resultList) {
                em.detach(altaTitular);
            }

            List<AltaTitular> returnList = new ArrayList<AltaTitular>();
            returnList.addAll(resultList);

            return returnList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }


    public void update(AltaTitular altaTitular) {
        if ( altaTitular == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        AltaTitular updated = em.merge(altaTitular);
        em.flush();
    }

    public List<AltaTitular> findPipesponsorFileByLotId(Long lotId) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from AltaTitular i where i.lotId =:lotId and i.procesado is null ").setParameter("lotId", lotId);

            List<AltaTitular> resultList = query.getResultList();

            return resultList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }

    }

    public int updateStatus(Long lotId, String status) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("update AltaTitular at set at.procesado = :status where at.lotId=:lotId").setParameter("lotId", lotId).setParameter("status", status);

            int response = query.executeUpdate();

            return response;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al updatear status de la data insertada de PipeSponsor.");
        }
    }

    public List<AltaTitular> findAltaTitByBrokerRutAndLotNumber(String brokerRut, Long lotNumber) throws PipeSponsorDBException {
        try {
            Query query = em.createQuery("select i from AltaTitular i where i.rutCorredor =:brokerRut and i.lote =:lotNumber ")
                    .setParameter("brokerRut", brokerRut).setParameter("lotNumber", lotNumber);

            List<AltaTitular> resultList = query.getResultList();

            return resultList;
        } catch (Exception e){
            throw new PipeSponsorDBException("Hubo un error al descargar la data de PipeSponsor.");
        }
    }
}