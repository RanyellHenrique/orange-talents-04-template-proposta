package br.com.zupperacademy.ranyell.proposta.aviso;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoClientRequest {

    @NotBlank
    private String destino;
    @NotNull @Future
    private LocalDate validoAte;

    public AvisoClientRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
