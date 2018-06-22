package cl.metlife.abm.business.managers.sftp;

public class SFTPResponse {

    private boolean successResponse;
    private String description;

    public SFTPResponse(boolean successResponse, String description) {
        this.successResponse = successResponse;
        this.description = description;
    }

    public boolean isSuccessResponse() {
        return successResponse;
    }

    public void setSuccessResponse(boolean successResponse) {
        this.successResponse = successResponse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
