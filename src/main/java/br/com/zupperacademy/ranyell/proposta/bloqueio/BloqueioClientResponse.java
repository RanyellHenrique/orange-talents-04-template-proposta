package br.com.zupperacademy.ranyell.proposta.bloqueio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioClientResponse {

    private String resultado;

    public BloqueioClientResponse(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
