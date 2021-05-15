package br.com.zupperacademy.ranyell.proposta.carteira;

public class CarteiraClientRequest {

    private String email;
    private TipoCarteira carteira;

    public CarteiraClientRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
