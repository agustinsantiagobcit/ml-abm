package cl.metlife.abm.business.managers.sftp.exception;

public class SFTPConnectionException extends Exception{
    public SFTPConnectionException(String message) {
        super(message);
    }

    public SFTPConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
