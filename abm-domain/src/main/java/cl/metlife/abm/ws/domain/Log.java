package cl.metlife.abm.ws.domain;

import java.util.Date;

public class Log {

    private Date date;
    private String brokerRut;
    private String level;
    private String step;
    private String message;

    public Log() {
    }

    public Log(Date date, String brokerRut, String level, String step, String message) {
        this.date = date;
        this.brokerRut = brokerRut;
        this.level = level;
        this.step = step;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBrokerRut() {
        return brokerRut;
    }

    public void setBrokerRut(String brokerRut) {
        this.brokerRut = brokerRut;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
