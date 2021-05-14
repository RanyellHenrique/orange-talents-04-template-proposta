package br.com.zupperacademy.ranyell.proposta.bloqueio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioClientRequest {

    private String sistemaResponsavel;

    public BloqueioClientRequest(@JsonProperty("sistemaResponsavel") String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
