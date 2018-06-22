package cl.metlife.abm.domain;

import cl.blueprintsit.framework.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by BluePrints Developer on 22-05-2017.
 */
@Entity
@Table(name = "ABM_BITACORA")
public class Log extends BaseEntity implements Serializable {

    public static final String LOG_LEVEL_INFO = "INFO";
    public static final String LOG_LEVEL_ERROR = "ERROR";
    public static final String LOG_STEP_EJECUCION_GENERAL = "EJECUCION GENERAL";
    public static final String LOG_STEP_SFTP = "SFTP";
    public static final String LOG_STEP_PIPESPONSOR = "PIPESPONSOR";
    public static final String LOG_STEP_FILENET = "FILENET";
    public static final String LOG_STEP_PREINGRESO = "PREINGRESO";
    private Long id;
    private String comment;
    private String step;
    private String trace;
    private String level;
    private Process process;
    private Long processId;
    private Lot lot;
    private Long lotId;
    private Long detailId;
    private Date date;
    private String brokerRut;
    private String username;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "GLOSA")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "PASO")
    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Basic
    @Column(name = "TRACE")
    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    @Basic
    @Column(name = "NIVEL")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "LOTE_ID")
    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    @Basic
    @Column(name = "LOTE_ID")
    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    @Basic
    @Column(name = "DETALLE_ID")
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROCESO_ID")
    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    @Basic
    @Column(name = "PROCESO_ID")
    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    @Basic
    @Column(name = "FECHA")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "RUT_CORREDOR")
    public String getBrokerRut() {
        return brokerRut;
    }

    public void setBrokerRut(String brokerRut) {
        this.brokerRut = brokerRut;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}