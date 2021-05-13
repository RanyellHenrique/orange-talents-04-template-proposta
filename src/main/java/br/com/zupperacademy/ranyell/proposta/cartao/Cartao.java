package br.com.zupperacademy.ranyell.proposta.cartao;

import br.com.zupperacademy.ranyell.proposta.proposta.Proposta;

import javax.persistence.*;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;

    @Enumerated(EnumType.STRING)
    private EstadoCartao estadoCartao;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Proposta proposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero, Proposta proposta) {
        this.numero = numero;
        this.proposta = proposta;
        this.estadoCartao = EstadoCartao.DESBLOQUEADO;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public EstadoCartao getEstadoCartao() {
        return estadoCartao;
    }

    public void bloquearCartao() {
        estadoCartao = EstadoCartao.BLOQUEADO;
    }
}