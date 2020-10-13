package br.com.snacksapi.controllers;

import br.com.snacksapi.model.Pedido;
import br.com.snacksapi.services.PedidoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/{clienteId}/{snackId}")
    public Pedido fazerPedido(@PathVariable Long clienteId,
                              @PathVariable Long snackId) {
        return this.pedidoService.fazerPedido(clienteId, snackId);
    }

    @GetMapping("/{pedidoId}")
    public Pedido buscarPedido(@PathVariable Long pedidoId) {
        return this.pedidoService.buscarPedido(pedidoId);
    }

    @DeleteMapping("/{pedidoId}")
    public void cancelarPedido(@PathVariable Long pedidoId) {
        this.pedidoService.cancelarPedido(pedidoId);
    }
}
