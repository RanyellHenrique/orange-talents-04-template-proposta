package br.com.zupperacademy.ranyell.proposta.biometria;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import br.com.zupperacademy.ranyell.proposta.compartilhado.IsBase64;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    @IsBase64
    private String biometria;

    public BiometriaRequest(@JsonProperty("biometria") String biometria) {
        this.biometria = biometria;
    }

    public String getBiometria() {
        return biometria;
    }

    public Biometria toModel(Cartao cartao) {
        var biometria = new String(Base64.getDecoder().decode(this.biometria));
        return  new Biometria(biometria, cartao);
    }
}
