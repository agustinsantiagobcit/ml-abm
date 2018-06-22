package cl.metlife.abm.controller;

import cl.blueprintsit.framework.web.BaseBean;
import cl.blueprintsit.framework.web.lazymodel.LogLazyDataModel;
import cl.metlife.abm.business.managers.DetailManager;
import cl.metlife.abm.business.managers.LogbookManager;
import cl.metlife.abm.business.managers.ProcessManager;
import cl.metlife.abm.domain.Detail;
import cl.metlife.abm.domain.Log;
import cl.metlife.abm.domain.Lot;
import cl.metlife.abm.domain.Process;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.ExcelExporter;
import org.primefaces.component.export.ExporterOptions;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Blueprints on 4/30/2018.
 */
@ManagedBean(name="logbookBean")
@ViewScoped
public class LogbookBean extends BaseBean {

    private Long processId;
    private LogLazyDataModel logs;
    private Process process;
    private String stackTrace;

    private Lot currentLot;
    private List<Detail> currentDetails;

    @EJB
    LogbookManager logbookManager;

    @EJB
    ProcessManager processManager;

    @EJB
    DetailManager detailManager;


    public void init(){
        if(logbookManager.countByProcessId(processId) == 0) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("noResults.xhtml");
                return;
            } catch (IOException e) {
                //do nothing
            }
        }

        logs = null;
    }

    public String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");

        return dateFormat.format(new Date());
    }

    public void setLot(Lot lot, Long detailId){
        this.currentLot = lot;

        if(detailId != null) {
            this.currentDetails = new ArrayList<Detail>();
            this.currentDetails.add(detailManager.getById(detailId));
        } else {
            this.currentDetails = detailManager.findByLotId(lot.getId());
        }
    }

    public void setLot(Lot lot){
        this.currentLot = lot;
        this.currentDetails = detailManager.findByLotId(lot.getId());
    }

    public int getIndexRowByDetail(Long detailId){
        return detailManager.getById(detailId).getIndex();
    }

    public void setLogTrace(Log log){
        this.stackTrace = log.getTrace();
    }

    public void refreshTable(){
        logs = null;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public LogLazyDataModel getLogs() {
        if (logs == null)
            logs = new LogLazyDataModel(logbookManager.getCurrentLogDAO(), processId);

        return logs;
    }

    public Process getProcess(){
        if (process==null)
            process = processManager.getById(processId);

        return process;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public Lot getCurrentLot() {
        return currentLot;
    }

    public void setCurrentLot(Lot currentLot) {
        this.currentLot = currentLot;
    }

    public List<Detail> getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDetails(List<Detail> currentDetails) {
        this.currentDetails = currentDetails;
    }
}


