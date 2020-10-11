package br.com.snacksapi.services.impl;

import br.com.snacksapi.exceptions.ClienteNotFoundException;
import br.com.snacksapi.exceptions.PedidoNotFoundException;
import br.com.snacksapi.exceptions.SnackNotFoundException;
import br.com.snacksapi.model.Cliente;
import br.com.snacksapi.model.Pedido;
import br.com.snacksapi.model.Snack;
import br.com.snacksapi.model.Troco;
import br.com.snacksapi.repository.ClienteRepository;
import br.com.snacksapi.repository.OrderRepository;
import br.com.snacksapi.repository.SnackRepository;
import br.com.snacksapi.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final ClienteRepository clienteRepository;
    private final SnackRepository snackRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(ClienteRepository clienteRepository,
                            SnackRepository snackRepository,
                            OrderRepository orderRepository) {
        this.clienteRepository = clienteRepository;
        this.snackRepository = snackRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Pedido placeOrder(Long clienteId, Long snackId) {
        Cliente cliente = this.clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("clienteId", clienteId));

        Snack snack = this.snackRepository.findById(snackId)
                .orElseThrow(() -> new SnackNotFoundException("snackId", snackId));

        Pedido pedido = new Pedido(cliente, snack);
        return this.orderRepository.save(pedido);
    }

    @Override
    public Troco cancelarPedido(Long pedidoId) {
        Pedido pedido = this.orderRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNotFoundException("pedidoId", pedidoId));

        Double snackPrice = pedido.getSnack().getPrice();
        Double clienteDinheiro = pedido.getCliente().getDinheiro();
        Troco troco = new Troco();
        if(clienteDinheiro > snackPrice) {
            troco.setCliente(pedido.getCliente());
            troco.setSnack(pedido.getSnack());
            troco.setQuantidade(clienteDinheiro - snackPrice);
        }
        this.orderRepository.deleteById(pedidoId);
        return troco;
    }
}
