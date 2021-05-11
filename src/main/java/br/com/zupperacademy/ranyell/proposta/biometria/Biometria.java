package br.com.zupperacademy.ranyell.proposta.biometria;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;

import javax.persistence.*;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String biometria;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String biometria, Cartao cartao) {
        this.biometria = biometria;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
