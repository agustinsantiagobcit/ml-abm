package cl.metlife.abm.domain;

import cl.blueprintsit.framework.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 22-05-2017.
 */
@Entity
@Table(name = "ABM_PROCESO")
public class Process extends BaseEntity implements Serializable {

    public static final String MANUAL_EXECUTION = "Manual";
    public static final String PROGRAMMED_EXECUTION = "Programado";
    private Long id;
    private String name;
    private boolean active;
    private boolean preIngresoActive;
    private boolean filenetActive;
    private boolean pipeSponsorActive;
    private boolean multiBroker;
    private ProcessType processType;
    private Long processTypeId;
    private Timer processTimer;
    private Long processTimerId;
    private Timer logTimer;
    private Long logTimerId;
    private String brokerName;
    private String brokerRut;
    private String host;
    private String port;
    private String user;
    private String password;
    private String errorMail;
    private boolean automaticSendPreingreso;
    private String fileDefinitionIdAltaTit;
    private String fileDefinitionIdAltaCar;
    private String fileDefinitionIdExcTit;
    private String fileDefinitionIdExcCar;
    private List<Log> logs;
    private boolean considerBarcodes;


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
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ACTIVO")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "PRE_INGRESO_ACTIVO")
    public boolean isPreIngresoActive() {
        return preIngresoActive;
    }

    public void setPreIngresoActive(boolean preIngresoActive) {
        this.preIngresoActive = preIngresoActive;
    }

    @Basic
    @Column(name = "FILENET_ACTIVO")
    public boolean isFilenetActive() {
        return filenetActive;
    }

    public void setFilenetActive(boolean filenetActive) {
        this.filenetActive = filenetActive;
    }

    @Basic
    @Column(name = "PIPE_SPONSOR_ACTIVO")
    public boolean isPipeSponsorActive() {
        return pipeSponsorActive;
    }

    public void setPipeSponsorActive(boolean pipeSponsorActive) {
        this.pipeSponsorActive = pipeSponsorActive;
    }

    @ManyToOne
    @JoinColumn(name = "TIPO_PROCESO_ID")
    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    @Basic
    @Column(name = "TIPO_PROCESO_ID")
    public Long getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(Long processTypeId) {
        this.processTypeId = processTypeId;
    }

    @Basic
    @Column(name = "TEMPORIZADOR_EJEC_ID")
    public Long getProcessTimerId() {
        return processTimerId;
    }

    public void setProcessTimerId(Long processTimerId) {
        this.processTimerId = processTimerId;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "TEMPORIZADOR_EJEC_ID")
    public Timer getProcessTimer() {
        return processTimer;
    }

    public void setProcessTimer(Timer processTimer) {
        this.processTimer = processTimer;
    }

    @Basic
    @Column(name = "TEMPORIZADOR_LOG_ID")
    public Long getLogTimerId() {
        return logTimerId;
    }

    public void setLogTimerId(Long logTimerId) {
        this.logTimerId = logTimerId;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "TEMPORIZADOR_LOG_ID")
    public Timer getLogTimer() {
        return logTimer;
    }

    public void setLogTimer(Timer logTimer) {
        this.logTimer = logTimer;
    }

    @Basic
    @Column(name = "CORREDOR_NOMBRE")
    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    @Basic
    @Column(name = "CORREDOR_RUT")
    public String getBrokerRut() {
        return brokerRut;
    }

    public void setBrokerRut(String brokerRut) {
        this.brokerRut = brokerRut;
    }

    @Basic
    @Column(name = "SFTP_HOST")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Basic
    @Column(name = "SFTP_PORT")
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Basic
    @Column(name = "SFTP_USER")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "SFTP_PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "ERROR_MAIL")
    public String getErrorMail() {
        return errorMail;
    }

    public void setErrorMail(String errorMail) {
        this.errorMail = errorMail;
    }

    @Transient
    public boolean isMultiBroker() {
        return multiBroker;
    }

    public void setMultiBroker(boolean multiBroker) {
        this.multiBroker = multiBroker;
    }

    @Basic
    @Column(name = "ENVIO_AUTOMATICO_LOGS_PI")
    public boolean isAutomaticSendPreingreso() {
        return automaticSendPreingreso;
    }

    public void setAutomaticSendPreingreso(boolean automaticSendPreingreso) {
        this.automaticSendPreingreso = automaticSendPreingreso;
    }

    @Basic
    @Column(name = "FILE_DEFINITION_ID_ALTATIT")
    public String getFileDefinitionIdAltaTit() {
        return fileDefinitionIdAltaTit;
    }

    public void setFileDefinitionIdAltaTit(String fileDefinitionIdAltaTit) {
        this.fileDefinitionIdAltaTit = fileDefinitionIdAltaTit;
    }

    @Basic
    @Column(name = "FILE_DEFINITION_ID_ALTACAR")
    public String getFileDefinitionIdAltaCar() {
        return fileDefinitionIdAltaCar;
    }

    public void setFileDefinitionIdAltaCar(String fileDefinitionIdAltaCar) {
        this.fileDefinitionIdAltaCar = fileDefinitionIdAltaCar;
    }

    @Basic
    @Column(name = "FILE_DEFINITION_ID_EXCTIT")
    public String getFileDefinitionIdExcTit() {
        return fileDefinitionIdExcTit;
    }

    public void setFileDefinitionIdExcTit(String fileDefinitionIdExcTit) {
        this.fileDefinitionIdExcTit = fileDefinitionIdExcTit;
    }

    @Basic
    @Column(name = "FILE_DEFINITION_ID_EXCCAR")
    public String getFileDefinitionIdExcCar() {
        return fileDefinitionIdExcCar;
    }

    public void setFileDefinitionIdExcCar(String fileDefinitionIdExcCar) {
        this.fileDefinitionIdExcCar = fileDefinitionIdExcCar;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "PROCESO_ID")
    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    @Basic
    @Column(name = "CONSIDERAR_CODIGOS_BARRA")
    public boolean isConsiderBarcodes() {
        return considerBarcodes;
    }

    public void setConsiderBarcodes(boolean considerBarcodes) {
        this.considerBarcodes = considerBarcodes;
    }
}