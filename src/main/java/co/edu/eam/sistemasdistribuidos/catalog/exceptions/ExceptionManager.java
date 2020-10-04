package co.edu.eam.sistemasdistribuidos.catalog.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionManager {

  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
  @ResponseBody
  public ErrorMessage handleBusinessException(HttpServletRequest req, BusinessException exc){
    return new ErrorMessage(exc.getMessage(), exc.getErrorCode());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorMessage handleNotFoundException(HttpServletRequest req, NotFoundException exc){
    return new ErrorMessage(exc.getMessage(), exc.getErrorCode());
  }

  @ExceptionHandler({InvalidFormatException.class, MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorMessage handleParamsError(HttpServletRequest req, Exception exc){
    return new ErrorMessage(exc.getMessage(), "bad_request");
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorMessage handleBadPathParams(HttpServletRequest req, Exception exc){
    return new ErrorMessage(exc.getMessage(), "bad_request");
  }

  @ExceptionHandler({NoSuchElementException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorMessage handleElementErrorException(HttpServletRequest req, Exception exc){
    return new ErrorMessage(exc.getMessage(), "element_doesnt_exist");
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorMessage handleMismatchException(HttpServletRequest req, Exception exc){
    return new ErrorMessage(exc.getMessage(), "mismatch_exception");
  }

}
