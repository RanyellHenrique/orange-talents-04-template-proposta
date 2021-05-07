package br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends  RuntimeException{

    private HttpStatus status;

    public ApiException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
