package br.com.snacksapi.exceptions;

public class DinheiroInsuficienteException extends RuntimeException {

    private final Double dinheiroCliente;
    private final Double snackPrice;

    public DinheiroInsuficienteException(Double dinheiroCliente, Double snackPrice) {
        this.dinheiroCliente = dinheiroCliente;
        this.snackPrice = snackPrice;
    }

    public Double getDinheiroCliente() {
        return dinheiroCliente;
    }

    public Double getSnackPrice() {
        return snackPrice;
    }
}
