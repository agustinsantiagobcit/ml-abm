package cl.metlife.abm.controller;

import cl.blueprintsit.framework.web.BaseBean;
import cl.blueprintsit.framework.web.lazymodel.LogLazyDataModel;
import cl.blueprintsit.framework.web.lazymodel.PreIngresoLogLazyDataModel;
import cl.metlife.abm.business.managers.LogbookManager;
import cl.metlife.abm.business.managers.ProcessManager;
import cl.metlife.abm.business.managers.sftp.SFTPManager;
import cl.metlife.abm.business.managers.sftp.exception.SFTPConnectionException;
import cl.metlife.abm.domain.Log;
import cl.metlife.abm.domain.Process;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Blueprints on 4/25/2018.
 * Preingreso Bean
 */

@ManagedBean(name="preIngresoBean")
@ViewScoped
public class PreIngresoSendBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    static final Logger logger = LoggerFactory.getLogger(PreIngresoSendBean.class);

    /* Form elements */
    private Date from;
    private Date to;
    private String multiBrokerRut;
    private Long processId;
    private String brokerRut;
    private String status;
    private String username;

    private DataTable dataTable;
    private PreIngresoLogLazyDataModel logs;
    private boolean showLogDatatable = false;

    /* Persistence Objects */
    @EJB
    LogbookManager logbookManager;

    @EJB
    ProcessManager processManager;

    @EJB
    SFTPManager sftpManager;


    @PostConstruct
    public void init(){

    }

    public void showButton(){
        Process process = processManager.getByMultiBrokerRut(multiBrokerRut);

        if(process != null)
            this.processId = process.getId();
        else {
            showError("Error. El rut ingresado no existe como multicorredor/corredor.", "El rut ingresado no existe como multicorredor/corredor.");
            return;
        }

        logs = null;
        showLogDatatable = true;

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('logDialogVar').show();");
    }

    public void sendToSFTP(){
        Process process = processManager.getByMultiBrokerRut(this.multiBrokerRut);

        try {
            Workbook wb = new LogExporter().export(this.dataTable, false, false);

            sftpManager.uploadLogFile(process.getHost(), Integer.valueOf(process.getPort()), process.getUser(), process.getPassword(), process.getBrokerRut(), wb);

            showMessage("globalMessage", "Éxito", "Se cargó correctamente el archivo de logs al servidor SFTP " + process.getHost() + ":" + Integer.valueOf(process.getPort()) + "/" + process.getUser());
        } catch (IOException e) {
            e.printStackTrace();
            showError("globalMessage", "Error E01", "Hubo un error al enviar archivo de logs al servidor SFTP " + process.getHost() + ":" + Integer.valueOf(process.getPort()) + "/" +  process.getUser());
        } catch (SFTPConnectionException e) {
            e.printStackTrace();
            showError("globalMessage", "Error E02", "Hubo un error al enviar archivo de logs al servidor SFTP " + process.getHost() + ":" + Integer.valueOf(process.getPort()) + "/" +  process.getUser());
        }

    }

    public void refreshTable(){
        this.logs = null;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getMultiBrokerRut() {
        return multiBrokerRut;
    }

    public void setMultiBrokerRut(String multiBrokerRut) {
        this.multiBrokerRut = multiBrokerRut;
    }

    public String getBrokerRut() {
        return brokerRut;
    }

    public void setBrokerRut(String brokerRut) {
        this.brokerRut = brokerRut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PreIngresoLogLazyDataModel getLogs() {
        if (logs == null)
            logs = new PreIngresoLogLazyDataModel(logbookManager.getCurrentLogDAO(), from, to, processId, brokerRut, status, username);

        return logs;
    }


    public void setLogs(PreIngresoLogLazyDataModel logs) {
        this.logs = logs;
    }

    public boolean isShowLogDatatable() {
        return showLogDatatable;
    }

    public void setShowLogDatatable(boolean showLogDatatable) {
        this.showLogDatatable = showLogDatatable;
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }
}

