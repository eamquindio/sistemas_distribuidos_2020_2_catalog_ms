package co.edu.eam.sistemasdistribuidos.catalog.exceptions;

public class ErrorMessage {
    private String message;
    private String code;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
