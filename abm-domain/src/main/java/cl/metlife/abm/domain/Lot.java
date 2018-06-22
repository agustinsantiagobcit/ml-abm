package cl.metlife.abm.domain;

import cl.blueprintsit.framework.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by BluePrints Developer on 22-05-2017.
 */
@Entity
@Table(name = "ABM_LOTE")
public class Lot extends BaseEntity implements Serializable {

    public static final String PROCESSING_SUFFIX = "Procesando_";
    public static final String ERROR_SUFFIX = "Error_";
    public static final String SUCCESSFUL_SUFFIX = "Procesado_";
    private Long id;
    private Date creationDate;
    private LotStatus lotStatus;
    private Long lotStatusId;
    private FileType fileType;
    private Long fileTypeId;
    private Process process;
    private Long processId;
    private String filename;
    private Date endDate;
    private String number;


    public Lot() {

    }

    public Lot(Long id, Date creationDate, Long lotStatusId, Long fileTypeId, Process process, Long processId, String filename, String number, Date endDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.lotStatusId = lotStatusId;
        this.fileTypeId = fileTypeId;
        this.process = process;
        this.processId = processId;
        this.filename = filename;
        this.endDate = endDate;
        this.number=number;
    }

    public Lot(Date creationDate, Long fileTypeId, Long processId, String filename, String number){
        this.id = null;
        this.creationDate = creationDate;
        this.fileTypeId = fileTypeId;
        this.processId = processId;
        this.filename = filename;
        this.lotStatusId = LotStatus.CREATED;
        this.number=number;
    }

    public Lot(Date creationDate, Long fileTypeId, Long processId, String filename, String number, Long status){
        this.id = null;
        this.creationDate = creationDate;
        this.fileTypeId = fileTypeId;
        this.processId = processId;
        this.filename = filename;
        this.lotStatusId = status;
        this.number=number;
    }


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
    @Column(name = "FECHA_CREACION")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ESTADO_LOTE_ID")
    public LotStatus getLotStatus() {
        return lotStatus;
    }

    public void setLotStatus(LotStatus lotStatus) {
        this.lotStatus = lotStatus;
    }

    @Basic
    @Column(name = "ESTADO_LOTE_ID")
    public Long getLotStatusId() {
        return lotStatusId;
    }

    public void setLotStatusId(Long lotStatusId) {
        this.lotStatusId = lotStatusId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_ARCHIVO_ID")
    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "TIPO_ARCHIVO_ID")
    public Long getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Long fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "PROCESO_ID")
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
    @Column(name = "NOMBRE_ARCHIVO")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "FECHA_FINALIZCION")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "NUMERO")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}