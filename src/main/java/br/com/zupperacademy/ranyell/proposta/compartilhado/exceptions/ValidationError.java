package br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends  StandardError{

    private List<FieldErrorValidation> fieldErrorValidations = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void addErro(FieldErrorValidation fieldError) {
        fieldErrorValidations.add(fieldError);
    }

    public List<FieldErrorValidation> getFieldErrorValidations() {
        return fieldErrorValidations;
    }
}
