package br.com.zupperacademy.ranyell.proposta.proposta;
import java.math.BigDecimal;

public class PropostaResponse {

    private String documento;
    private String email;
    private String nome;
    private BigDecimal salario;
    private String endereco;
    private EstadoProposta estadoProposta;
    private String cartao;

    public PropostaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.salario = proposta.getSalario();
        this.endereco = proposta.getEndereco();
        this.estadoProposta = proposta.getEstadoProposta();
        this.cartao = proposta.getCartao();
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getEndereco() {
        return endereco;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }

    public String getCartao() {
        return cartao;
    }
}
