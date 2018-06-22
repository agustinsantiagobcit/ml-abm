package cl.metlife.abm.ws.domain;

import java.util.List;

public class ProcessLog {

    private Long lotId;
    private String brokerRut;
    private String movementType;
    private String lotNumber;
    private String status;
    private String error;

    private List<Log> logs;

    public ProcessLog() {}

    public ProcessLog(String error) {
        this.error = error;
    }

    public ProcessLog(Long lotId, String brokerRut, String movementType, String lotNumber, String status) {
        this.lotId = lotId;
        this.brokerRut = brokerRut;
        this.movementType = movementType;
        this.lotNumber = lotNumber;
        this.status = status;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getBrokerRut() {
        return brokerRut;
    }

    public void setBrokerRut(String brokerRut) {
        this.brokerRut = brokerRut;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}
