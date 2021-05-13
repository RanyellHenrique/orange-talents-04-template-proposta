package br.com.zupperacademy.ranyell.proposta.bloqueio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioRequest {

    private String sistemaResponsavel;

    public BloqueioRequest(@JsonProperty("sistemaResponsavel") String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
