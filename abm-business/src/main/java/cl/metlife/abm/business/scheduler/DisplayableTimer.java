package cl.metlife.abm.business.scheduler;

import javax.ejb.ScheduleExpression;
import javax.ejb.Timer;
import java.util.Date;

/**
 * Created by BluePrints Developer on 12-05-2017.
 */
public class DisplayableTimer{

    Object info;
    Date nextTimeout;
    ScheduleExpression schedule;
    Timer originalTimer;

    public DisplayableTimer(Timer timer) {
        info = timer.getInfo();
        nextTimeout = timer.getNextTimeout();
        schedule = timer.getSchedule();
        originalTimer = timer;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Date getNextTimeout() {
        return nextTimeout;
    }

    public void setNextTimeout(Date nextTimeout) {
        this.nextTimeout = nextTimeout;
    }

    public ScheduleExpression getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleExpression schedule) {
        this.schedule = schedule;
    }

    public Timer getOriginalTimer() {
        return originalTimer;
    }

    public void setOriginalTimer(Timer originalTimer) {
        this.originalTimer = originalTimer;
    }
}