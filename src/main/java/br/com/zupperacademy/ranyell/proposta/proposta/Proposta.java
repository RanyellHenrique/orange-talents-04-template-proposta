package br.com.zupperacademy.ranyell.proposta.proposta;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import br.com.zupperacademy.ranyell.proposta.compartilhado.CriptografaAtributo;
import org.springframework.security.crypto.encrypt.Encryptors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CriptografaAtributo.class)
    private String documento;
    private String email;
    private String nome;
    private BigDecimal salario;
    private String endereco;

    @Enumerated(EnumType.STRING)
    private EstadoProposta estadoProposta;

    @OneToOne(mappedBy = "proposta", cascade = CascadeType.PERSIST)
    private Cartao cartao;

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

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }

    public void setEstadoProposta(EstadoProposta estadoProposta) {
        this.estadoProposta = estadoProposta;
    }


}
