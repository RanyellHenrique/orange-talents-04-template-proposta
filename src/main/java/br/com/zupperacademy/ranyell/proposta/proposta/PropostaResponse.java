package br.com.zupperacademy.ranyell.proposta.proposta;

public class PropostaResponse {

    private String nome;
    private EstadoProposta estadoProposta;

    public PropostaResponse(Proposta proposta) {
        this.nome = proposta.getNome();
        this.estadoProposta = proposta.getEstadoProposta();
    }

    public String getNome() {
        return nome;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }
}
