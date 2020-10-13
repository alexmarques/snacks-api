package br.com.snacksapi.services;

import br.com.snacksapi.model.Pedido;

public interface PedidoService {
    Pedido fazerPedido(Long clienteId, Long snackId);

    void cancelarPedido(Long pedidoId);

    Pedido buscarPedido(Long pedidoId);
}
