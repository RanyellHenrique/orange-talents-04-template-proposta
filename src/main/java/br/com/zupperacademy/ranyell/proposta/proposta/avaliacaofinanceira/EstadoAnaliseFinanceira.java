package br.com.zupperacademy.ranyell.proposta.proposta.avaliacaofinanceira;

import br.com.zupperacademy.ranyell.proposta.proposta.EstadoProposta;

public enum EstadoAnaliseFinanceira {

    COM_RESTRICAO(EstadoProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(EstadoProposta.ELEGIVEL);

    private EstadoProposta estadoProposta;

    EstadoAnaliseFinanceira(EstadoProposta estadoProposta) {
        this.estadoProposta = estadoProposta;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }
}
