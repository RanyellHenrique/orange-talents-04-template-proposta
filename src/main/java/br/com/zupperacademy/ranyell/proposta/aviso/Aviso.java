package br.com.zupperacademy.ranyell.proposta.aviso;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String destino;
    @NotNull @Future
    private LocalDate validoAte;
    @NotNull
    private LocalDate criadoEm;
    @NotBlank
    private String ip;
    @NotBlank
    private String userAgent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

    /*
     * @Deprecated apenas para o uso do hibernate
     * */
    @Deprecated
    public Aviso() {
    }

    public Aviso(String destino, LocalDate validoAte, String ip, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.validoAte = validoAte;
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
        this.criadoEm = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getNumeroDoCartao() {
        return cartao.getNumero();
    }

    public String getDestino() {
        return destino;
    }
}
