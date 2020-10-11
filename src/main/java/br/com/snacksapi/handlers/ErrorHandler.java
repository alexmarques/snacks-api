package br.com.snacksapi.handlers;

import br.com.snacksapi.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ValidationErrorResponse onClienteNotFoundException(NotFoundException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(new Error(e.getFieldName(), e.getFieldName() + " [" + e.getId() + "] não encontrado"));
        return error;
    }

}
