package cl.metlife.abm.business.scheduler;

import cl.metlife.abm.business.execution.ProcessExecutionManager;
import cl.metlife.abm.domain.Process;
import cl.metlife.abm.persistence.dao.ProcessTypeDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Stateless
public class ProcessStarter {

    @EJB
    ProcessExecutionManager processExecutionManager;


    public Long manualStart(Process process, String username) {
        processExecutionManager.execute(process, username, Process.MANUAL_EXECUTION);
        return process.getId();
    }

    public void programmaticStart(Process process) {
        processExecutionManager.execute(process, null,Process.PROGRAMMED_EXECUTION);
    }

}
