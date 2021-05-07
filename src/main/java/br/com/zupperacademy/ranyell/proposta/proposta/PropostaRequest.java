package br.com.zupperacademy.ranyell.proposta.proposta;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank @CpfOuCnpj
    private String cpfOuCnpj;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String endereco;
    @NotNull @PositiveOrZero
    private BigDecimal salario;

    public PropostaRequest(String cpfOuCnpj, String email, String endereco, BigDecimal salario) {
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return  new Proposta(cpfOuCnpj, email, endereco, salario);
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }
}
