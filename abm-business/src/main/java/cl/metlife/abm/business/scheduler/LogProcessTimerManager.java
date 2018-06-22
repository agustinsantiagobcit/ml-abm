package cl.metlife.abm.business.scheduler;


import cl.metlife.abm.business.managers.ProcessManager;
import cl.metlife.abm.domain.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by BluePrints Developer on 08-11-2016.
 */
@Singleton
public class LogProcessTimerManager {

    @Resource
    TimerService timerService;


    @EJB
    ProcessManager processManager;

    @EJB
    LogProcessStarter logProcessStarter;


    private Logger logger = LoggerFactory.getLogger(LogProcessTimerManager.class);


    public void cancelForProcess(Process process) {
        Timer timer = getTimerForProcess(process);
        if(timer!= null)
            timer.cancel();
    }

    public Collection<Timer> getTimers(){
        return timerService.getTimers();
    }


    @Timeout
    public void programmaticTimeout(Timer timer) {
        Process oldProcess =null;
        try {
            oldProcess = (Process) timer.getInfo();
        }catch(Throwable t){
            timer.cancel();
            logger.error("Matando timer de proceso ivnalido");
            return;
        }
        if(oldProcess==null){
            timer.cancel();
            logger.error("Matando timer de proceso ivnalido, en condicion que no deberia darse");
            return;
        }

        Process currentProcess = processManager.getById((oldProcess.getId()));

        logger.debug("Timer {} {} ",timer.getNextTimeout(),timer.getTimeRemaining());
        logProcessStarter.programmaticStart(currentProcess);
    }

    public void addTimer( Process process, ScheduleExpression scheduleExpression) {

        cancelForProcess(process);

        logger.info("activando timer para proceso " + process.getId());
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setPersistent(true);
        timerConfig.setInfo(process);
        timerService.createCalendarTimer(scheduleExpression,timerConfig);

    }


    public ScheduleExpression getScheduleExpressionForProcess(Process process) {
        Timer timer = getTimerForProcess(process);

        if(timer != null)
            return timer.getSchedule();
        return null;
    }

    public Date getNextExecutionForProcess(Process process) {
        Timer timer = getTimerForProcess(process);
        if (timer != null)
            return timer.getNextTimeout();
        return null;
    }

    public Timer getTimerForProcess(Process process) {

        logger.debug("buscando timer para proceso id "+process.getId());
        for (Timer timer : getTimers()) {

            if(!checkValidTimer(timer))
                continue;

            if(timer.getInfo() instanceof Process)
            {
                if ( ((Process) timer.getInfo()).getId().equals(process.getId()))
                {

                    return timer;
                }
            }
        }


        return null;
    }

    public Timer getTimerForDisplayableTimer(DisplayableTimer displayableTimer) {

        logger.debug("buscando timer para displayableTimer "+ displayableTimer);
        Collection<Timer> timers = getTimers();
        for (Timer timer : timers) {
            if ( timer.equals(displayableTimer.getOriginalTimer()))
            {
                return timer;
            }
        }

        return null;
    }

    private boolean checkValidTimer(Timer timer) {
        timer.getNextTimeout();


        try{
            if(timer.getInfo() instanceof Process){
                Process p = (Process) timer.getInfo();
                if (p.getId() != null)
                    return true;

            }
        }catch (Throwable t){
            logger.error("Error exotico con timers",t);
            timer.cancel();
        }

        logger.warn("timer, invalido "+timer);
        return false;
    }

    public void cancelForDisplayableTimer(DisplayableTimer displayableTimer) {

        Timer timer = getTimerForDisplayableTimer(displayableTimer);

        if(timer!= null)
            timer.cancel();
    }

    public List<DisplayableTimer> getDisplayableTimers() {
        List<DisplayableTimer> displayableTimers = new ArrayList<DisplayableTimer>();
            Collection<Timer> timers = getTimers();
            for (Timer timer : timers) {
                displayableTimers.add(new DisplayableTimer(timer));
            }
        return displayableTimers;
    }

    public List<SimpleDisplayableTimer> getSimpleDisplayableTimers() {
        List<SimpleDisplayableTimer> displayableTimers = new ArrayList<SimpleDisplayableTimer>();
        Collection<Timer> timers = getTimers();
        for (Timer timer : timers) {
            displayableTimers.add(new SimpleDisplayableTimer(timer));
        }
        return displayableTimers;
    }

    // FIXME
    public List<DisplayableTimer> getTimersFromService() {
        List<DisplayableTimer> displayableTimers = new ArrayList<DisplayableTimer>();
        Collection<Timer> timers = getTimers();
        for (Timer timer : timers) {
            displayableTimers.add(new DisplayableTimer(timer));
        }
        return displayableTimers;
    }
}
