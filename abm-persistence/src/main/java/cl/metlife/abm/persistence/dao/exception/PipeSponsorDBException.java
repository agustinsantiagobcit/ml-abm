package cl.metlife.abm.persistence.dao.exception;

public class PipeSponsorDBException extends Exception{
    public PipeSponsorDBException(String message) {
        super(message);
    }

    public PipeSponsorDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
