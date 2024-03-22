package com.hegaro.medicalapp.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
              HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var errors = ex.getBindingResult().getAllErrors();
        List<String> errorsMessage = new ArrayList<>();
        errors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .forEach(errorsMessage::add);

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), "Validación fállida.", errorsMessage.toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> modelExceptionHandler
            (Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ModelNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> modelExceptionHandler
            (ModelNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
                return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}