package cl.metlife.abm.business.daemon.filenet.checker;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by BluePrints Developer on 23-05-2016.
 */
@Singleton
public class FilenetCheckerDaemonTimer {

    @EJB
    FilenetCheckerDaemonJob service;

    static final Logger logger = LoggerFactory.getLogger(FilenetCheckerDaemonTimer.class);

    @Schedule(second = "*/5", minute="*/1",hour="*", persistent=false)
    public void doWork() {
        service.startProcess();
    }

}
