package cl.metlife.abm.ws.domain;

public class ProcessResult {

    public static final String STATUS_ERROR = "Error";
    public static final String STATUS_SUCCESS = "Success";


    private String status;
    private String errorDetail;


    public ProcessResult() {
    }

    public ProcessResult(String status, String errorDetail) {
        this.status = status;
        this.errorDetail = errorDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
