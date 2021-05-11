package br.com.zupperacademy.ranyell.proposta.cartao;

import br.com.zupperacademy.ranyell.proposta.proposta.Proposta;

public class CartaoResponse {

    private String id;
    private String emitidoEm;

    public CartaoResponse(String id, String emitidoEm) {
        this.id = id;
        this.emitidoEm = emitidoEm;
    }

    public String getId() {
        return id;
    }

    public String getEmitidoEm() {
        return emitidoEm;
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(id, proposta);
    }
}
