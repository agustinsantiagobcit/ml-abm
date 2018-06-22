package cl.metlife.abm.business.managers.filenet.exception;

public class EncodeException extends Exception{
    public EncodeException(String message) {
        super(message);
    }

    public EncodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
