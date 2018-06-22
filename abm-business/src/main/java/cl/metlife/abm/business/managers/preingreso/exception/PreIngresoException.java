package cl.metlife.abm.business.managers.preingreso.exception;

public class PreIngresoException extends Exception{
    public PreIngresoException(String message) {
        super(message);
    }

    public PreIngresoException(String message, Throwable cause) {
        super(message, cause);
    }
}
