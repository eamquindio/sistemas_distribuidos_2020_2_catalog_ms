package co.edu.eam.sistemasdistribuidos.catalog.exceptions;


public class NotFoundException extends BusinessException{


    public NotFoundException(String message, String ErrorCode) {

        super(message, ErrorCode);
    }
    
    public NotFoundException(String message) {
    super(message);
    setErrorCode("entity_not_found");
  }

}
