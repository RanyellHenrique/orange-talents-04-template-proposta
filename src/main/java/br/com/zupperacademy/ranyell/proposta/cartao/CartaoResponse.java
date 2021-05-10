package br.com.zupperacademy.ranyell.proposta.cartao;

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
}
