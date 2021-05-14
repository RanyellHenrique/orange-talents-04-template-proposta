package br.com.zupperacademy.ranyell.proposta.aviso;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequest {

    @NotBlank
    private String destino;
    @Future @NotNull
    private LocalDate validoAte;

    public AvisoRequest(String destino, LocalDate terminaEm) {
        this.destino = destino;
        this.validoAte = terminaEm;
    }

    public Aviso toModel(String userAgent, String ip, Cartao cartao) {
        return new Aviso(destino, validoAte, ip, userAgent, cartao);
    }
}
