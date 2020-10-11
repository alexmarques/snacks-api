package br.com.snacksapi.services;

import br.com.snacksapi.model.Pedido;
import br.com.snacksapi.model.Troco;

public interface OrderService {
    Pedido placeOrder(Long clienteId, Long snackId);

    Troco cancelarPedido(Long pedidoId);
}
