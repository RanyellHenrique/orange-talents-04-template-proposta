package br.com.zupperacademy.ranyell.proposta.avisodeviagem;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class AvisoDeViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String destino;
    @NotNull @Future
    private LocalDateTime terminaEm;
    @NotNull
    private LocalDateTime criadoEm;
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
    public AvisoDeViagem() {
    }

    public AvisoDeViagem(String destino, LocalDateTime terminaEm, String ip, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.terminaEm = terminaEm;
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
        this.criadoEm = LocalDateTime.now();
    }


}
