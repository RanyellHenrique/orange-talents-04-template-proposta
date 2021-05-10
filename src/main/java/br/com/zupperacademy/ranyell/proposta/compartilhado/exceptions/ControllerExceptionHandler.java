package br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;


@RestControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new  ValidationError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "Validation Exception", e.getMessage(), request.getRequestURI());

        for(FieldError erro : e.getBindingResult().getFieldErrors()) {
            err.addErro(new FieldErrorValidation(erro.getField(), erro.getDefaultMessage()));
        }
        logger.error("Erro(s) de validação");
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<StandardError>  apiException(ApiException e, HttpServletRequest request) {
        StandardError err = new StandardError(Instant.now(), e.getStatus().value(), "Api Exception",
                e.getMessage(), request.getRequestURI());
        logger.error(e.getMessage());
        return  ResponseEntity.status(e.getStatus()).body(err);
    }
}