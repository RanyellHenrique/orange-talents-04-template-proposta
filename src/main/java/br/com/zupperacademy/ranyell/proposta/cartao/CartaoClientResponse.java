package br.com.zupperacademy.ranyell.proposta.cartao;

import br.com.zupperacademy.ranyell.proposta.proposta.Proposta;

import java.time.LocalDateTime;

public class CartaoClientResponse {

    private String id;
    private LocalDateTime emitidoEm;

    public CartaoClientResponse(String id, LocalDateTime emitidoEm) {
        this.id = id;
        this.emitidoEm = emitidoEm;
    }

    public String getId() {
        return id;
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(id, proposta, emitidoEm);
    }
}
