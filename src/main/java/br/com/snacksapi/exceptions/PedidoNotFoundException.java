package br.com.snacksapi.exceptions;

public class PedidoNotFoundException extends NotFoundException {
    public PedidoNotFoundException(String fieldName, Long pedidoId) {
        super(fieldName, pedidoId);
    }
}
