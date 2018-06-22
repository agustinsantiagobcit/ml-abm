package cl.metlife.abm.domain;

import cl.blueprintsit.framework.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 22-05-2017.
 */
@Entity
@Table(name = "ABM_DETALLE")
public class Detail extends BaseEntity implements Serializable {

    private Long id;
    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;
    private String column6;
    private String column7;
    private String column8;
    private String column9;
    private String column10;
    private String column11;
    private String column12;
    private String column13;
    private String column14;
    private String column15;
    private String column16;
    private String column17;
    private String column18;
    private String column19;
    private String column20;
    private String column21;
    private String column22;
    private String column23;
    private String column24;
    private String column25;
    private String column26;
    private String column27;
    private String column28;
    private String rutaPdf;
    private String idFilenet;
    private Long lotId;
    private Long movementTypeId;
    private Long statusId;
    private int index;

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
    @Column(name = "COLUMN_1")
    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    @Basic
    @Column(name = "COLUMN_2")
    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    @Basic
    @Column(name = "COLUMN_3")
    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    @Basic
    @Column(name = "COLUMN_4")
    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    @Basic
    @Column(name = "COLUMN_5")
    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    @Basic
    @Column(name = "COLUMN_6")
    public String getColumn6() {
        return column6;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    @Basic
    @Column(name = "COLUMN_7")
    public String getColumn7() {
        return column7;
    }

    public void setColumn7(String column7) {
        this.column7 = column7;
    }

    @Basic
    @Column(name = "COLUMN_8")
    public String getColumn8() {
        return column8;
    }

    public void setColumn8(String column8) {
        this.column8 = column8;
    }

    @Basic
    @Column(name = "COLUMN_9")
    public String getColumn9() {
        return column9;
    }

    public void setColumn9(String column9) {
        this.column9 = column9;
    }

    @Basic
    @Column(name = "COLUMN_10")
    public String getColumn10() {
        return column10;
    }

    public void setColumn10(String column10) {
        this.column10 = column10;
    }

    @Basic
    @Column(name = "COLUMN_11")
    public String getColumn11() {
        return column11;
    }

    public void setColumn11(String column11) {
        this.column11 = column11;
    }

    @Basic
    @Column(name = "COLUMN_12")
    public String getColumn12() {
        return column12;
    }

    public void setColumn12(String column12) {
        this.column12 = column12;
    }

    @Basic
    @Column(name = "COLUMN_13")
    public String getColumn13() {
        return column13;
    }

    public void setColumn13(String column13) {
        this.column13 = column13;
    }

    @Basic
    @Column(name = "COLUMN_14")
    public String getColumn14() {
        return column14;
    }

    public void setColumn14(String column14) {
        this.column14 = column14;
    }

    @Basic
    @Column(name = "COLUMN_15")
    public String getColumn15() {
        return column15;
    }

    public void setColumn15(String column15) {
        this.column15 = column15;
    }

    @Basic
    @Column(name = "COLUMN_16")
    public String getColumn16() {
        return column16;
    }

    public void setColumn16(String column16) {
        this.column16 = column16;
    }

    @Basic
    @Column(name = "COLUMN_17")
    public String getColumn17() {
        return column17;
    }

    public void setColumn17(String column17) {
        this.column17 = column17;
    }

    @Basic
    @Column(name = "COLUMN_18")
    public String getColumn18() {
        return column18;
    }

    public void setColumn18(String column18) {
        this.column18 = column18;
    }

    @Basic
    @Column(name = "COLUMN_19")
    public String getColumn19() {
        return column19;
    }

    public void setColumn19(String column19) {
        this.column19 = column19;
    }

    @Basic
    @Column(name = "COLUMN_20")
    public String getColumn20() {
        return column20;
    }

    public void setColumn20(String column20) {
        this.column20 = column20;
    }

    @Basic
    @Column(name = "COLUMN_21")
    public String getColumn21() {
        return column21;
    }

    public void setColumn21(String column21) {
        this.column21 = column21;
    }

    @Basic
    @Column(name = "COLUMN_22")
    public String getColumn22() {
        return column22;
    }

    public void setColumn22(String column22) {
        this.column22 = column22;
    }

    @Basic
    @Column(name = "COLUMN_23")
    public String getColumn23() {
        return column23;
    }

    public void setColumn23(String column23) {
        this.column23 = column23;
    }

    @Basic
    @Column(name = "COLUMN_24")
    public String getColumn24() {
        return column24;
    }

    public void setColumn24(String column24) {
        this.column24 = column24;
    }

    @Basic
    @Column(name = "COLUMN_25")
    public String getColumn25() {
        return column25;
    }

    public void setColumn25(String column25) {
        this.column25 = column25;
    }

    @Basic
    @Column(name = "COLUMN_26")
    public String getColumn26() {
        return column26;
    }

    public void setColumn26(String column26) {
        this.column26 = column26;
    }

    @Basic
    @Column(name = "COLUMN_27")
    public String getColumn27() {
        return column27;
    }

    public void setColumn27(String column27) {
        this.column27 = column27;
    }

    @Basic
    @Column(name = "COLUMN_28")
    public String getColumn28() {
        return column28;
    }

    public void setColumn28(String column28) {
        this.column28 = column28;
    }

    @Basic
    @Column(name = "RUTA_PDF")
    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

    @Basic
    @Column(name = "ID_FILENET")
    public String getIdFilenet() {
        return idFilenet;
    }

    public void setIdFilenet(String idFilenet) {
        this.idFilenet = idFilenet;
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
    @Column(name = "ESTADO_DETALLE_ID")
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "ROW_INDEX")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}