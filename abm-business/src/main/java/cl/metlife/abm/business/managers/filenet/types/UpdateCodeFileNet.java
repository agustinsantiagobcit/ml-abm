package cl.metlife.abm.business.managers.filenet.types;

/**
 * Created by BlueprintsIT on 6/1/2016.
 */
public class UpdateCodeFileNet {
    private String poliza;
    private String codBarra;

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public UpdateCodeFileNet(String poliza, String codBarra) {
        this.poliza = poliza;
        this.codBarra = codBarra;
    }
}
