package cl.metlife.abm.business.managers.preingreso;

import cl.metlife.abm.business.managers.DetailManager;
import cl.metlife.abm.business.managers.LogbookManager;
import cl.metlife.abm.business.managers.LotManager;
import cl.metlife.abm.business.managers.preingreso.exception.PreIngresoException;
import cl.metlife.abm.domain.*;
import cl.metlife.abm.domain.Process;
import cl.metlife.ws.clients.pipesponsor.*;
import cl.metlife.ws.clients.preingreso.bulk.*;
import cl.metlife.ws.clients.preingreso.bulk.ObjectFactory;
import org.apache.commons.io.FilenameUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PreIngresoManager {

    PreingresoMapper preingresoMapper;

    @EJB
    PreIngresoServiceManager preIngresoServiceManager;

    @EJB
    LogbookManager logbookManager;

    @EJB
    DetailManager detailManager;

    @EJB
    LotManager lotManager;

    public PreIngresoManager(){
        preingresoMapper = new PreingresoMapper();
    }

    public boolean registerFilenet(Detail detail, String filenetCode){
        Lot lot = lotManager.getById(detail.getLotId());
        String sesionId = preIngresoServiceManager.getSesionId();

        Holder<Integer> errorNumber = new Holder<Integer>();
        Holder<String> errorDescription = new Holder<String>();
        Holder<ErrorBo> errorBo = new Holder<ErrorBo>();
        if(lot.getFileTypeId().equals(FileType.ALTA_TITULAR))
            preIngresoServiceManager.getBulkService(sesionId).fileNetCodigoGrabar(sesionId, 0,
                    Integer.valueOf(detail.getColumn1()), Integer.valueOf(detail.getColumn2()), Integer.valueOf(detail.getColumn5()), Integer.valueOf(detail.getColumn6()),
                    Integer.valueOf(detail.getColumn7()), Integer.valueOf(detail.getColumn8()), filenetCode, errorNumber, errorDescription, errorBo);
        else if(lot.getFileTypeId().equals(FileType.ALTA_CARGA)){
            preIngresoServiceManager.getBulkService(sesionId).fileNetCodigoGrabar(sesionId, 0,
                    Integer.valueOf(detail.getColumn1()), Integer.valueOf(detail.getColumn2()), Integer.valueOf(detail.getColumn5()), Integer.valueOf(detail.getColumn6()),
                    Integer.valueOf(detail.getColumn7()), Integer.valueOf(detail.getColumn8()), filenetCode, errorNumber, errorDescription, errorBo);
        } else if(lot.getFileTypeId().equals(FileType.BAJA_TITULAR)){
            preIngresoServiceManager.getBulkService(sesionId).fileNetCodigoGrabar(sesionId, 0,
                    Integer.valueOf(detail.getColumn1()), Integer.valueOf(detail.getColumn2()), Integer.valueOf(detail.getColumn3()), Integer.valueOf(detail.getColumn4()),
                    null, null, filenetCode, errorNumber, errorDescription, errorBo);
        } else if(lot.getFileTypeId().equals(FileType.BAJA_CARGA)){
            preIngresoServiceManager.getBulkService(sesionId).fileNetCodigoGrabar(sesionId, 0,
                    Integer.valueOf(detail.getColumn1()), Integer.valueOf(detail.getColumn2()), Integer.valueOf(detail.getColumn3()), Integer.valueOf(detail.getColumn4()),
                    null, null, filenetCode, errorNumber, errorDescription, errorBo);
        }

        if(errorNumber.value == 0){
            logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_INFO, Log.LOG_STEP_PREINGRESO, "Se actualizó correctamente el código filenet en PreIngreso", lot, detail, null, null);
            return true;
        } else
            logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PREINGRESO, "Hubo un error al registrar el código filenet en PreIngreso [DESCRIPTION: " + errorDescription.value + "].", lot, detail, null, null);

        return false;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean createAlta(Lot lot, Detail detail, Process process, String user) throws JAXBException, SQLException, PreIngresoException {
        try {
            String sesionId = preIngresoServiceManager.getSesionId();

            MovtoAltaBo movtoAltaBo = preingresoMapper.makeMovtoAltoBo(detail, lot, user);

            if(movtoAltaBo != null){
                ErrorBo errorBo = preIngresoServiceManager.getBulkService(sesionId).crearAlta(sesionId, movtoAltaBo);

                if(errorBo.getNumero() == 0){
                    logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_INFO, Log.LOG_STEP_PREINGRESO, "Se creo el alta correctamente en PreIngreso.", lot, detail, null, null);
                    return true;
                } else {
                    logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_PREINGRESO, "Hubo un error al crear el alta en PreIngreso. [CODE: " + errorBo.getNumero() + ", DESCRIPTION: " + errorBo.getDescripcion() + "]", lot, detail, null, null);
                    return false;
                }

            } else {
                logbookManager.error(process, Log.LOG_STEP_PREINGRESO, "Hubo un error al parsear el objeto de alta a preingreso", null);
                throw new PreIngresoException("Hubo un error al parsear el objeto de alta a preingreso");
            }
        } catch (Exception e) {
            logbookManager.error(process, Log.LOG_STEP_PREINGRESO, "Hubo un error al crear el alta en preingreso", null);
            throw new PreIngresoException("Hubo un error al crear el alta en preingreso", e);
        }
    }

    private void updateLotToError(Lot lot) {
        lot.setLotStatusId(LotStatus.ERROR);
        lot.setLotStatus(null);

        lotManager.update(lot);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean createBaja(Lot lot, Detail detail, Process process, String user) throws JAXBException, SQLException, PreIngresoException {
        try {
            String sesionId = preIngresoServiceManager.getSesionId();
            MovtoBajaBo movtoBajaBo = preingresoMapper.makeMovtoBajaBo(detail, lot, user);

            if(movtoBajaBo != null){
                ErrorBo errorBo = preIngresoServiceManager.getBulkService(sesionId).crearBaja(sesionId, movtoBajaBo);

                if(errorBo.getNumero() == 0){
                    logbookManager.log(lot.getProcess(), Log.LOG_LEVEL_INFO, Log.LOG_STEP_PREINGRESO, "Se creo la baja correctamente en PreIngreso.", lot, detail, null, null);
                    return true;
                } else {
                    logbookManager.error(process, Log.LOG_STEP_PREINGRESO, "Hubo un error con el servicio web de PreIngreso: " + errorBo.getDescripcion(), null);
                    return false;
                }
            } else {
                logbookManager.error(process, Log.LOG_STEP_PREINGRESO, "Hubo un error al parsear el objeto de baja a preingreso", null);
                throw new PreIngresoException("Hubo un error al parsear el objeto de baja a preingreso");
            }
        } catch (Exception e) {
            throw new PreIngresoException("Hubo un error al crear el baja en preingreso", e);
        }
    }


}
