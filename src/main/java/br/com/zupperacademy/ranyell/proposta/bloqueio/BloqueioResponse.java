package br.com.zupperacademy.ranyell.proposta.bloqueio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioResponse {

    private String resultado;

    public BloqueioResponse(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
