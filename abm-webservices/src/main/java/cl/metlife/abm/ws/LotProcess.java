package cl.metlife.abm.ws;

import cl.metlife.abm.business.execution.WebServiceExecutionManager;
import cl.metlife.abm.business.managers.LogbookManager;
import cl.metlife.abm.business.managers.LotManager;
import cl.metlife.abm.business.managers.PipeTablesManager;
import cl.metlife.abm.domain.AltaCarga;
import cl.metlife.abm.domain.AltaTitular;
import cl.metlife.abm.domain.ExclusionTitular;
import cl.metlife.abm.domain.ExclusionCarga;
import cl.metlife.abm.domain.Lot;
import cl.metlife.abm.persistence.dao.exception.PipeSponsorDBException;
import cl.metlife.abm.ws.domain.Log;
import cl.metlife.abm.ws.domain.ProcessLog;
import cl.metlife.abm.ws.domain.ProcessResult;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService
public class LotProcess {

    @EJB
    private WebServiceExecutionManager webServiceExecutionManager;

    @EJB
    private PipeTablesManager pipeTablesManager;

    @EJB
    private LogbookManager logbookManager;

    @EJB
    private LotManager lotManager;


    /**
     * MEtodo que gatilla la subida de un lote
     * @param rut
     * @param lotNumber
     * @param movementType
     * @param file
     * @return
     */
    @WebMethod
    public ProcessResult processLot(
            @WebParam(name = "brokerRut") String rut,
            @WebParam(name = "lotNumber")  String lotNumber,
            @WebParam(name = "date")  Date date,
            @WebParam(name = "user")  String user,
            @WebParam(name = "movementType") String movementType,
            @WebParam(name = "file") byte[] file){

        if(rut == null || rut.isEmpty())
            return new ProcessResult(ProcessResult.STATUS_ERROR,"Missing parameter: brokerRut");
        if(movementType == null || movementType.isEmpty())
            return new ProcessResult(ProcessResult.STATUS_ERROR,"Missing parameter: movementType");
        if(file == null || file.length==0)
            return new ProcessResult(ProcessResult.STATUS_ERROR,"Missing parameter: file");

        return webServiceExecutionManager.execute(rut, lotNumber, date, movementType, file, user);
    }


    @WebMethod
    public ProcessLog getLotLog(
            @WebParam(name = "brokerRut") String rut,
            @WebParam(name = "lotNumber")  String lotNumber){

        ProcessLog processLog = new ProcessLog(null, rut, null, lotNumber, null);

        if(rut == null || rut.isEmpty())
            return new ProcessLog("Missing parameter: brokerRut");
        if(lotNumber == null || lotNumber.isEmpty())
            return new ProcessLog("Missing parameter: lotNumber");

        try {
            List<Lot> lotes = lotManager.findByRutAndLotNumber(rut, lotNumber);

            if(lotes.isEmpty())
                return new ProcessLog("Sin resultados");

            for (Lot lote : lotes) {
                return populateProcessLog(processLog, lote);
            }

        } catch (Exception e) {
            e.printStackTrace();
            processLog.setStatus("ERROR");
        }

        return processLog;
    }

    private ProcessLog populateProcessLog(ProcessLog processLog, Lot lot) {
        processLog.setLotId(lot.getId());
        processLog.setMovementType(lot.getFileType().getName());
        processLog.setStatus(lot.getLotStatus().getName());

        List<Log> logs = mapLogsitos(logbookManager.findByLotId(lot.getId()));
        processLog.setLogs(logs);

        return processLog;
    }

    private List<Log> mapLogsitos(List<cl.metlife.abm.domain.Log> byProcessId) {
        List<Log> logsitos = new ArrayList<Log>();

        for (cl.metlife.abm.domain.Log log : byProcessId) {
            Log lordJotecillo = new Log();

            lordJotecillo.setBrokerRut(log.getBrokerRut());
            lordJotecillo.setDate(log.getDate());
            lordJotecillo.setLevel(log.getLevel());
            lordJotecillo.setStep(log.getStep());
            lordJotecillo.setMessage(log.getComment());

            logsitos.add(lordJotecillo);
        }

        return logsitos;
    }
}
