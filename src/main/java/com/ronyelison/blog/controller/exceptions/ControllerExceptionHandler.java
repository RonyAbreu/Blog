package com.ronyelison.blog.controller.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroResponse> entityNotFoundErro(EntityNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroResponse erroResponse = new ErroResponse(exception.getMessage(), status.getReasonPhrase());
        return ResponseEntity.status(status).body(erroResponse);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErroResponse> entityExistErro(EntityExistsException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroResponse erroResponse = new ErroResponse(exception.getMessage(), status.getReasonPhrase());
        return ResponseEntity.status(status).body(erroResponse);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<ErroResponse> noPermissionErro(NoPermissionException exception){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErroResponse erroResponse = new ErroResponse(exception.getMessage(), status.getReasonPhrase());
        return ResponseEntity.status(status).body(erroResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> validationErro(MethodArgumentNotValidException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError erroResponse = new ValidationError("Erro de validação", status.getReasonPhrase());
        for (FieldError erro : exception.getFieldErrors()){
            erroResponse.addFieldMessage(erro.getField(), erro.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(erroResponse);
    }
}
