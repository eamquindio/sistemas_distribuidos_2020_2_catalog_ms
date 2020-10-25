package co.edu.eam.sistemasdistribuidos.catalog.exceptions;


public class BusinessException extends RuntimeException{

    private String ErrorCode;
    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(String message, String ErrorCode) {
        super(message);
        this.ErrorCode = ErrorCode;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

}
