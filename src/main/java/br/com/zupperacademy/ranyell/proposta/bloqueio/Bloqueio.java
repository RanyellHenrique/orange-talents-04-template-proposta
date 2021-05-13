package br.com.zupperacademy.ranyell.proposta.bloqueio;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant bloqueadoEm;
    private String ip;
    private String userAgent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String ip, String userAgent, Cartao cartao) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
        this.bloqueadoEm = Instant.now();
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
