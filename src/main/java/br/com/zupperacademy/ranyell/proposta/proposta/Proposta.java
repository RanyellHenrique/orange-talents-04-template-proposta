package br.com.zupperacademy.ranyell.proposta.proposta;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private String email;
    private String nome;
    private BigDecimal salario;
    private String endereco;

    @Enumerated(EnumType.STRING)
    private EstadoProposta estadoProposta;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String endereco, BigDecimal salario, String nome) {
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getNome() {
        return  nome;
    }

    public void setEstadoProposta(EstadoProposta estadoProposta) {
        this.estadoProposta = estadoProposta;
    }
}
