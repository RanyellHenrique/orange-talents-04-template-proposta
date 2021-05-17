package br.com.zupperacademy.ranyell.proposta.proposta;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank @CpfOuCnpj
    private String documento;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String endereco;
    @NotNull @PositiveOrZero
    private BigDecimal salario;

    @NotBlank
    private String nome;

    public PropostaRequest(String documento, String email, String endereco, BigDecimal salario, String nome) {
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
        this.nome = nome;
    }

    public Proposta toModel() {
        return  new Proposta(documento, email, endereco, salario, nome);
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }
}
