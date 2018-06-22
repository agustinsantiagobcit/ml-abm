package cl.metlife.abm.business.managers;

import cl.metlife.abm.domain.Detail;
import cl.metlife.abm.domain.Log;
import cl.metlife.abm.domain.Lot;
import cl.metlife.abm.domain.Process;
import cl.metlife.abm.persistence.dao.LogDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Stateless
public class LogbookManager {

    static private final Logger LOGGER = LoggerFactory.getLogger(LogbookManager.class);

    @EJB
    LogDAO logDAO;

    public LogDAO getCurrentLogDAO(){
        return this.logDAO;
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void log(Process process, String level, String step, String message, String username){

        Log entry = makeLogEntry(process, level, step, message, null, null, null, username);

        logDAO.create(entry);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void log(Process process, String level, String step, String message, Throwable throwable, String username){

        Log entry = makeLogEntry(process, level, step, message, throwable, null, null, username);

        logDAO.create(entry);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void log(Process process, String level, String step, String message, Lot lot, Throwable throwable, String username){

        Log entry = makeLogEntry(process, level, step, message, throwable, lot, null, username);

        logDAO.create(entry);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void log(Process process, String level, String step, String message, Lot lot, Detail detail, Throwable throwable, String username){

        Log entry = makeLogEntry(process, level, step, message, throwable, lot, detail, username);

        logDAO.create(entry);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void info(Process process, String step, String message, String username){
        log( process, "INFO", step, message, username);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void info(Process process, String broker, String step, String message, String username){
        log( process, "INFO", step, message, username);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void error(Process process, String step, String message, String username){
        log( process, "ERROR", step, message, username);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void error(Process process, String broker, String step, String message, String username){
        log( process, "ERROR", step, message, username);
    }

    private Log makeLogEntry(Process process, String level, String step, String message, Throwable throwable, Lot lot, Detail detail, String username) {
        Log entry = new Log();

        entry.setLevel(level);
        entry.setComment(message);
        entry.setDate( new Date());
        if(throwable != null)
            entry.setTrace(stackTraceToString(throwable));

        if(detail != null)
            entry.setDetailId(detail.getId());
        if(lot != null)
            entry.setLotId(lot.getId());

        entry.setStep(step);
        entry.setProcessId(process.getId());
        entry.setUsername(username);

        return entry;
    }

    private String stackTraceToString(Throwable t){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    public List<Log> findByProcessId(Long processId){
        return logDAO.findByProcess(processId);
    }

    public int countByProcessId(Long processId){
        return logDAO.countByProcess(processId);
    }

    public List<Log> findByProcessIdFromDateAndStep(Long processId, Date dateFrom, String step){
        return logDAO.findByProcessIdFromDateAndStep(processId, dateFrom, step);

    }

    public List<Log> findByProcessIdAndStatus(Long processId, Long lotId, String logLevelError) {
        return logDAO.findByProcessAndStatus(processId, lotId, logLevelError);
    }

    public List<Log> findByLotId(Long lotId) {
        return logDAO.findByLotId(lotId);
    }
}
