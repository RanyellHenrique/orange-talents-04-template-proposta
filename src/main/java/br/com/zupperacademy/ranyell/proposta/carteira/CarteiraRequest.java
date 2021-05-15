package br.com.zupperacademy.ranyell.proposta.carteira;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CarteiraRequest {

    @NotNull
    private TipoCarteira tipoCarteira;
    @NotBlank @Email
    private String email;

    public CarteiraRequest(TipoCarteira tipoCarteira, String email) {
        this.tipoCarteira = tipoCarteira;
        this.email = email;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(email, tipoCarteira, cartao);
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }
}
