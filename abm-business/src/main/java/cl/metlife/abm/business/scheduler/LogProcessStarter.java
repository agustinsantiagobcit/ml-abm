package cl.metlife.abm.business.scheduler;

import cl.metlife.abm.business.execution.LogProcessExecutionManager;
import cl.metlife.abm.business.execution.ProcessExecutionManager;
import cl.metlife.abm.domain.Process;
import cl.metlife.abm.persistence.dao.ProcessTypeDAO;

import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import java.util.Date;

/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Stateless
public class LogProcessStarter {

    @EJB
    LogProcessExecutionManager logProcessExecutionManager;

    @EJB
    ProcessTypeDAO processTypeDAO;

    @EJB
    LogProcessTimerManager logProcessTimerManager;

    public void programmaticStart(Process process) {
        Timer timer = logProcessTimerManager.getTimerForProcess(process);
        Date nextTimeout = timer.getNextTimeout();
        Date lastTimeout = getLastTimeout(nextTimeout);

        timer.getTimeRemaining();

        logProcessExecutionManager.execute(process, lastTimeout, null,Process.PROGRAMMED_EXECUTION);


    }

    private Date getLastTimeout(Date nextTimeout) {
        Date now = new Date();
        Date f2 = nextTimeout;

        Long diff = nextTimeout.getTime() - now.getTime();

        return new Date( now.getTime() - diff);
    }

}
