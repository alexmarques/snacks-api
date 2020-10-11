package br.com.snacksapi.controllers;

import br.com.snacksapi.model.Pedido;
import br.com.snacksapi.model.Troco;
import br.com.snacksapi.services.OrderService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final OrderService orderService;

    public PedidoController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{clienteId}/{snackId}")
    public Pedido placeOrder(Long clienteId, Long snackId) {
        return this.orderService.placeOrder(clienteId, snackId);
    }

    @DeleteMapping("/{pedidoId}")
    public Troco cancelarPedido(Long pedidoId) {
        return this.orderService.cancelarPedido(pedidoId);
    }
}
