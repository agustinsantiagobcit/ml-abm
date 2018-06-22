package cl.metlife.abm.controller;

import cl.blueprintsit.framework.web.BaseBean;
import cl.metlife.abm.business.managers.ABMConfigurationManager;
import cl.metlife.abm.business.managers.ProcessManager;
import cl.metlife.abm.business.managers.sftp.SFTPManager;
import cl.metlife.abm.business.managers.sftp.SFTPResponse;
import cl.metlife.abm.domain.*;
import cl.metlife.abm.domain.Process;
import org.primefaces.event.FlowEvent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 1/27/2016.
 */
@ManagedBean(name="editProcessBean")
@ViewScoped
public class EditProcessBean extends BaseBean {

    private Long processId;
    private Process editItem;

    // Wizard shits
    private boolean skip;

    // View haxxors
    private boolean inited = false;

    @EJB
    SFTPManager sftpManager;

    @EJB
    ProcessManager processManager;

    @EJB
    ABMConfigurationManager configManager;


    public void init(){
        if(inited)
            return;

        inited = true;
        if(processId == null)
            newProcess();
        else
            editItem = processManager.getById(processId);
    }

    public void testSFTP() {
        // Validate nulls
        if (!isValidSFTPParameters()) {
            super.showInfo("growl", "Error", "Debe ingresar Host, Puerto, Usuario y Password para realizar una prueba a la conexión.");
            return;
        }

        /* Check connection */
        try {
            SFTPResponse response = sftpManager.testSFTP(editItem.getHost(), Integer.valueOf(editItem.getPort()), editItem.getUser(), editItem.getPassword());

            if(response.isSuccessResponse())
                super.showInfo("growl", "Éxito", "Se logró la conexión exitósamente.");
            else {
                super.showError("growl", "Error", "La conexión falló: " + ((response.getDescription().contains("Auth cancel")) ? "Credenciales incorrectas." : "No hay conexión con el host."));
            }
        } catch (Exception e) {
            super.showError("growl", "Error", "La conexión falló.");
        }
    }

    public String save() {
        this.editItem.setBrokerRut(this.editItem.getBrokerRut().replace(".", ""));

        try {
            if ( this.editItem.getId() == null) {
                this.processManager.create(this.editItem);
            } else {
                this.processManager.update(this.editItem);
            }
        } catch (Exception e){
            super.showError("growl", "Error", "Hubo un error al guardar el corredor. Favor consulte con su administrador");
            return null;
        }

        super.showInfo("messages", "Éxito", "Se creó correctamente el corredor.");

        return "processes?faces-redirect=true";
    }

    private void newProcess() {
        editItem = new Process();
        editItem.setPassword("");

        //Long id, String year, String month, String day, String dayOfWeek, String hour, String minute, String second, List<String> days, List<String> months, Date parsedDate
        List<String> days = new ArrayList<String>();
        days = populateDays(days);

        List<String> months = new ArrayList<String>();
        months = populateMonths(months);

        editItem.setFileDefinitionIdAltaTit(configManager.getByKey(ABMConfiguration.ALTA_TITULAR_DEFAULT_CODE).getValor());
        editItem.setFileDefinitionIdAltaCar(configManager.getByKey(ABMConfiguration.ALTA_CARGA_DEFAULT_CODE).getValor());
        editItem.setFileDefinitionIdExcTit(configManager.getByKey(ABMConfiguration.EXCLUSION_TITULAR_DEFAULT_CODE).getValor());
        editItem.setFileDefinitionIdExcCar(configManager.getByKey(ABMConfiguration.EXCLUSION_CARGA_DEFAULT_CODE).getValor());

        editItem.setLogTimer(new Timer(null, "*", null, null, "*", null, null, null, days, months, null));
        editItem.setProcessTimer(new Timer(null, "*", null, null, "*", null, null, null, days, months, null));

        editItem.setProcessTypeId(ProcessType.PROCESS_TYPE_NORMAL);
        editItem.setMultiBroker(false);
    }

    private List<String> populateDays(List<String> days) {
        for (int i = 1; i <= 7; i++) {
            days.add(""+i);
        }

        return days;
    }

    private List<String> populateMonths(List<String> days) {
        for (int i = 1; i <= 12; i++) {
            days.add(""+i);
        }

        return days;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Process getEditItem() {
        return editItem;
    }

    public void setEditItem(Process editItem) {
        this.editItem = editItem;
    }

    public SFTPManager getSftpManager() {
        return sftpManager;
    }

    public void setSftpManager(SFTPManager sftpManager) {
        this.sftpManager = sftpManager;
    }

    public boolean isValidSFTPParameters() {
        if(this.editItem.getHost() == null || this.editItem.getHost().trim().equals("") || this.editItem.getPort() == null
                || this.editItem.getPort().trim().equals("") || this.editItem.getUser() == null || this.editItem.getUser().trim().equals("")
                || this.editItem.getPassword() == null || this.editItem.getPassword().trim().equals(""))
            return false;

        return true;
    }
}
