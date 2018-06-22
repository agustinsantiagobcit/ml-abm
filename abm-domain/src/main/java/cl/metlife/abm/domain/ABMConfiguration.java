package cl.metlife.abm.domain;

import cl.blueprintsit.framework.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "ABM_CONFIGURACION")
public class ABMConfiguration extends BaseEntity implements Serializable {

    public static final String FILE_ID_ALTA_TITULAR = "fileid.altatitular";
    public static final String FILE_ID_ALTA_CARGA = "fileid.altacarga";
    public static final String FILE_ID_BAJA_TITULAR = "fileid.bajatitular";
    public static final String FILE_ID_BAJA_CARGA = "fileid.bajacarga";
    public static final String FILENET_CREATORTASKID = "filenet.creatortaskid";
    public static final String FILENET_APPLICATIONID = "filenet.applicationid";
    public static final String ALTA_TITULAR_BARCODE_INDEX = "altatitular.barcode.index";
    public static final String ALTA_CARGA_BARCODE_INDEX = "altacarga.barcode.index";
    public static final String BAJA_TITULAR_BARCODE_INDEX = "bajatitular.barcode.index";
    public static final String BAJA_CARGA_BARCODE_INDEX = "bajacarga.barcode.index";
    public static final String ALTA_TITULAR_DEFAULT_CODE = "altatitular.default.code";
    public static final String ALTA_CARGA_DEFAULT_CODE = "altacarga.default.code";
    public static final String EXCLUSION_TITULAR_DEFAULT_CODE = "exclusiontitular.default.code";
    public static final String EXCLUSION_CARGA_DEFAULT_CODE = "exclusioncarga.default.code";
    public static final String EXECUTION_ENVIRONMENT_FOLDER = "execution.environment.folder";
    public static final String LOCAL_FOLDER_ROOT = "local.folder.root";
    public static final String ERROR_MAIL_TEMPLATE_PATH = "error-mail.template.path";
    public static final String ERROR_MAIL_HOST = "error-mail.host";
    public static final String ERROR_MAIL_PORT = "error-mail.port";
    public static final String ERROR_MAIL_MAILFROM = "error-mail.mail-from";
    public static final String ERROR_MAIL_MAILNAME = "error-mail.mail-name";
    public static final String ERROR_MAIL_MAILUSER = "error-mail.mail-user";
    public static final String ERROR_MAIL_MAILPASS = "error-mail.mail-password";
    public static final String ERROR_MAIL_MAILSUBJECT = "error-mail.mail-subject";

    private Long id;
    private String name;
    private String valor;
    private String descripcion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "VALOR", nullable = true, length = 255)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 1000)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
