package cl.metlife.abm.business.scheduler;

import cl.metlife.abm.business.execution.LogProcessExecutionManager;
import cl.metlife.abm.business.managers.ProcessManager;
import cl.metlife.abm.domain.Process;
import cl.metlife.abm.persistence.dao.ProcessTypeDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Startup
@Singleton
public class InitApplication {

    @EJB
    ProcessManager processManager;

    @PostConstruct
    public void init() {
        List<Process> processList = processManager.findActiveProcess();

        for (Process process : processList) {
            processManager.startProgrammaticExecution(process);
        }
    }

}
