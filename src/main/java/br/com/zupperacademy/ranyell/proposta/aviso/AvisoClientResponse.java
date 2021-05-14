package br.com.zupperacademy.ranyell.proposta.aviso;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvisoClientResponse {

    private String resultado;

    public AvisoClientResponse(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
