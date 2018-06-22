package cl.metlife.abm.business.scheduler;

import javax.ejb.ScheduleExpression;
import javax.ejb.Timer;

/**
 * Created by BluePrints Developer on 12-05-2017.
 */
public class SimpleDisplayableTimer {

    private ScheduleExpression schedule;

    public SimpleDisplayableTimer() {
    }

    public SimpleDisplayableTimer(Timer timer) {
        schedule = timer.getSchedule();
    }

    public ScheduleExpression getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleExpression schedule) {
        this.schedule = schedule;
    }

}