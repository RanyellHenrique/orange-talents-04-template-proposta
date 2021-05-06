package br.com.zupperacademy.ranyell.proposta.proposta;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpfOuCnpj;
    private String email;
    private BigDecimal salario;
    private String endereco;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String cpfOuCnpj, String email, String endereco, BigDecimal salario) {
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }
}
