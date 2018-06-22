package cl.metlife.abm.controller;

import cl.blueprintsit.framework.web.BaseBean;
import cl.blueprintsit.framework.web.auth.ABMAuthenticationBean;
import cl.metlife.abm.business.managers.ProcessManager;
import cl.metlife.abm.business.scheduler.ProcessStarter;
import cl.metlife.abm.domain.Process;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;

/**
 * Created by Blueprints on 1/27/2016.
 */
@ManagedBean(name="processBean")
@ViewScoped
public class ProcessesBean extends BaseBean {

    private List<Process> processList;

    @EJB
    ProcessManager processManager;

    @EJB
    ProcessStarter processStarter;

    @ManagedProperty(value="#{authenticationBean}")
    private ABMAuthenticationBean authenticationBean;


    public String start(Process process){
        Long processId = processStarter.manualStart(process, authenticationBean.getLoggedUser().getUsername());

        return "logbook?faces-redirect=true&processId="+processId;
    }

    public void startProgrammaticExecution(Process process){
        if(process.getProcessTimer().getHour() == null || process.getProcessTimer().getHour().equals("") || process.getProcessTimer().getMinute() == null
                || process.getProcessTimer().getMinute().equals("")){
            showError("Error","El corredor no tiene configurado hora/minuto de ejecución para activar el proceso programado.");
            return;
        } else if(process.isAutomaticSendPreingreso()){
            if(process.getLogTimer().getHour() == null || process.getLogTimer().getHour().equals("") || process.getLogTimer().getMinute() == null
                    || process.getLogTimer().getMinute().equals("")){
                showError("Error","El corredor tiene activada la opción de envío automatico a PreIngreso, pero no tiene configurada hora/minuto de ejecución para activar el proceso programado.");
                return;
            }
        }

        processManager.startProgrammaticExecution(process);
        processManager.activeProcess(process, true);
    }

    public void stopProgrammaticExecution(Process process){
        processManager.stopProgrammaticExecution(process);
        processManager.activeProcess(process, false);
    }

    public Date getNextProcessExecutionForProcess(Process process) {
        return processManager.getNextProcessExecutionForProcess(process);
    }

    public Date getNextLogSendExecutionForProcess(Process process) {
        return processManager.getNextLogSendExecutionForProcess(process);
    }

    public List<Process> getProcessList() {
        if(processList == null)
            processList = processManager.findAll();

        return processList;
    }

    public void delete(Process process) {
        if(this.processManager.delete(process)){
            showInfo("Aviso","Se eliminó el corredor exitosamente.");
            resetProcessesList();
        } else
            showError("Error","No se pudo eliminar el corredor.");
    }

    public void resetProcessesList(){
        processList = null;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }

    public ABMAuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public void setAuthenticationBean(ABMAuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }
}
