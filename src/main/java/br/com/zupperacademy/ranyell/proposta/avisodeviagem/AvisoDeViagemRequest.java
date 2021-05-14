package br.com.zupperacademy.ranyell.proposta.avisodeviagem;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AvisoDeViagemRequest {

    @NotBlank
    private String destino;
    @Future @NotNull
    private LocalDateTime terminaEm;

    public AvisoDeViagemRequest(String destino, LocalDateTime terminaEm) {
        this.destino = destino;
        this.terminaEm = terminaEm;
    }

    public AvisoDeViagem toModel(String userAgent, String ip, Cartao cartao) {
        return new AvisoDeViagem(destino, terminaEm, ip, userAgent, cartao);
    }
}
