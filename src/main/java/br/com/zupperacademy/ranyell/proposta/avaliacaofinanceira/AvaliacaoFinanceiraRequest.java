package br.com.zupperacademy.ranyell.proposta.avaliacaofinanceira;

import br.com.zupperacademy.ranyell.proposta.proposta.CpfOuCnpj;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AvaliacaoFinanceiraRequest {

    @CpfOuCnpj @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @Positive @NotNull
    private Long idProposta;

    public AvaliacaoFinanceiraRequest(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
