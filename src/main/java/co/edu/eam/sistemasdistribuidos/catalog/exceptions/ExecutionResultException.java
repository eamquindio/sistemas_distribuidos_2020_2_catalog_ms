package co.edu.eam.sistemasdistribuidos.catalog.exceptions;

public class ExecutionResultException extends BusinessException{

    public ExecutionResultException(String message, String ErrorCode) {

        super(message, ErrorCode);
    }
}

