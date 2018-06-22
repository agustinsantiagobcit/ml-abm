package cl.metlife.abm.domain;

import cl.blueprintsit.framework.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 22-05-2017.
 */
@Entity
@Table(name = "ABM_ESTADO_DETALLE")
public class DetailsStatus extends BaseEntity implements Serializable {

    public static final Long CREATED = 1L;
    public static final Long LOADED_IN_FILENET = 2L;
    public static final Long FINALIZED = 3L;
    public static final Long ERROR = 4L;
    private Long id;
    private String name;

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
}