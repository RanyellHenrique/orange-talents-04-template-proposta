package br.com.zupperacademy.ranyell.proposta.carteira;

public class CarteiraClientResponse {

    private String resultado;
    private String id;

    public CarteiraClientResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
