package co.edu.eam.sistemasdistribuidos.catalog.exceptions;



import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionManager {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorMessage handleNotFoundException(HttpServletRequest req, NotFoundException exc){
    return new ErrorMessage(exc.getMessage(), exc.getErrorCode());
  }

  @ExceptionHandler({InvalidFormatException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorMessage handleParamsError(HttpServletRequest req, Exception exc){
    return new ErrorMessage(exc.getMessage(), "bad_request");
  }
  
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public ErrorMessage handleBusinessException(HttpServletRequest req, BusinessException exc){
        return new ErrorMessage(exc.getMessage(), exc.getErrorCode());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorMessage handlePostException(HttpServletRequest req, Exception exc){
        return new ErrorMessage(exc.getMessage(), "Post_Url_Incorrect");
    }

    @ExceptionHandler(ExecutionResultException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public ErrorMessage handleWithoutResultException(HttpServletRequest req, ExecutionResultException exc){
        return new ErrorMessage(exc.getMessage(), exc.getErrorCode());
    }

}
