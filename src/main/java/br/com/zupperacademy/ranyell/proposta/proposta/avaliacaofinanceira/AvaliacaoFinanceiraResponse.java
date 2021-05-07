package br.com.zupperacademy.ranyell.proposta.proposta.avaliacaofinanceira;

import br.com.zupperacademy.ranyell.proposta.proposta.CpfOuCnpj;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AvaliacaoFinanceiraResponse {

    @CpfOuCnpj
    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @Positive
    @NotNull
    private Long idProposta;
    @NotNull
    private EstadoAnaliseFinanceira resultadoSolicitacao;

    public AvaliacaoFinanceiraResponse(String documento, String nome, Long idProposta, EstadoAnaliseFinanceira estado) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = estado;
    }

    public EstadoAnaliseFinanceira getResultadoSolicitacao() {
        return resultadoSolicitacao;
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
