package br.com.zupperacademy.ranyell.proposta.cartao;

import br.com.zupperacademy.ranyell.proposta.proposta.Proposta;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private LocalDateTime emitidoEm;

    @Enumerated(EnumType.STRING)
    private EstadoCartao estadoCartao;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Proposta proposta;

    /*
    * @Deprecated apenas para o uso do hibernate
    * */
    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero, Proposta proposta, LocalDateTime emitidoEm) {
        this.numero = numero;
        this.proposta = proposta;
        this.estadoCartao = EstadoCartao.DESBLOQUEADO;
        this.emitidoEm = emitidoEm;
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

    public String getEmailProposta() {
        return proposta.getEmail();
    }
}
