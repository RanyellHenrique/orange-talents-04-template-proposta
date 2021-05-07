package br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions;

public class FieldErrorValidation {

    private String field;
    private String message;

    public FieldErrorValidation(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}