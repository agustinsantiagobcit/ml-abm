package cl.metlife.abm.business.managers;

import cl.metlife.abm.business.managers.sftp.SFTPManager;
import cl.metlife.abm.business.managers.sftp.exception.SFTPConnectionException;
import cl.metlife.abm.domain.*;
import cl.metlife.abm.domain.Process;
import cl.metlife.abm.persistence.dao.*;
import cl.metlife.abm.persistence.dao.exception.PipeSponsorDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class PipeTablesManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PipeTablesManager.class);

    @EJB
    AltaTitularDAO altaTitularDAO;

    @EJB
    AltaCargaDAO altaCargaDAO;

    @EJB
    ExclusionTitularDAO exclusionTitularDAO;

    @EJB
    ExclusionCargaDAO exclusionCargaDAO;


    public List<AltaTitular> findAltaTitUnprocessedRows(Long lotId) throws PipeSponsorDBException {
        return altaTitularDAO.findPipesponsorFileByLotId(lotId);
    }

    public List<AltaCarga> findAltaCarUnprocessedRows(Long lotId) throws PipeSponsorDBException {
        return altaCargaDAO.findPipesponsorFileByLotId(lotId);
    }

    public List<ExclusionTitular> findExcTitUnprocessedRows(Long lotId) throws PipeSponsorDBException {
        return exclusionTitularDAO.findPipesponsorFileByLotId(lotId);
    }

    public List<ExclusionCarga> findExcCarUnprocessedRows(Long lotId) throws PipeSponsorDBException {
        return exclusionCargaDAO.findPipesponsorFileByLotId(lotId);
    }

    public List<AltaTitular> findAltaTitByBrokerRutAndLotNumber(String brokerRut, Long lotNumber) throws PipeSponsorDBException {
        return altaTitularDAO.findAltaTitByBrokerRutAndLotNumber(brokerRut, lotNumber);
    }

    public List<AltaCarga> findAltaCarByBrokerRutAndLotNumber(String brokerRut, Long lotNumber) throws PipeSponsorDBException {
        return altaCargaDAO.findAltaCarByBrokerRutAndLotNumber(brokerRut, lotNumber);
    }

    public List<ExclusionTitular> findExcTitByBrokerRutAndLotNumber(String brokerRut, Long lotNumber) throws PipeSponsorDBException {
        return exclusionTitularDAO.findExcTitByBrokerRutAndLotNumber(brokerRut, lotNumber);
    }

    public List<ExclusionCarga> findExcCarByBrokerRutAndLotNumber(String brokerRut, Long lotNumber) throws PipeSponsorDBException {
        return exclusionCargaDAO.findExcCarByBrokerRutAndLotNumber(brokerRut, lotNumber);
    }

    public void update(AltaTitular altaTitular) {
        altaTitularDAO.update(altaTitular);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void update(AltaCarga altaCarga) {
        altaCargaDAO.update(altaCarga);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void update(ExclusionTitular exclusionTitular) {
        exclusionTitularDAO.update(exclusionTitular);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void update(ExclusionCarga exclusionCarga) {
        exclusionCargaDAO.update(exclusionCarga);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateStatusAltaTit(Long lotId, String status) throws PipeSponsorDBException {
        altaTitularDAO.updateStatus(lotId, status);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateStatusAltaCar(Long lotId, String status) throws PipeSponsorDBException {
        altaCargaDAO.updateStatus(lotId, status);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateStatusExcTit(Long lotId, String status) throws PipeSponsorDBException {
        exclusionTitularDAO.updateStatus(lotId, status);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateStatusExcCar(Long lotId, String status) throws PipeSponsorDBException {
        exclusionCargaDAO.updateStatus(lotId, status);
    }
}