package cl.metlife.abm.domain;

import cl.blueprintsit.framework.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 22-05-2017.
 */
@Entity
@Table(name = "ABM_TIPO_MOVIMIENTO_ARCHIVO")
public class FileType extends BaseEntity implements Serializable {

    public static final Long ALTA_TITULAR = 1L; //1	Alta de Asegurados y Cargas
    public static final Long ALTA_CARGA = 2L; //2	Alta de Cargas
    public static final Long BAJA_TITULAR = 3L; //3	Baja de Asegurados y Cargas
    public static final Long BAJA_CARGA = 4L; //4	Baja de Cargas
    public static final int ALTA_TITULAR_INT = 1;
    public static final int ALTA_CARGA_INT = 2;
    public static final int BAJA_TITULAR_INT = 3;
    public static final int BAJA_CARGA_INT = 4;
    private Long id;
    private String name;
    private String fileId;
    private String wsId;

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
    @Column(name = "FILEID")
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "WS_ID")
    public String getWsId() {
        return wsId;
    }

    public void setWsId(String wsId) {
        this.wsId = wsId;
    }
}