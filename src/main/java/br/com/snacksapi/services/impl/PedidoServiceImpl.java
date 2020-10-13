package br.com.snacksapi.services.impl;

import br.com.snacksapi.exceptions.ClienteNotFoundException;
import br.com.snacksapi.exceptions.DinheiroInsuficienteException;
import br.com.snacksapi.exceptions.PedidoNotFoundException;
import br.com.snacksapi.exceptions.SnackNotFoundException;
import br.com.snacksapi.model.Cliente;
import br.com.snacksapi.model.Pedido;
import br.com.snacksapi.model.Snack;
import br.com.snacksapi.model.StatusPedido;
import br.com.snacksapi.repository.ClienteRepository;
import br.com.snacksapi.repository.PedidoRepository;
import br.com.snacksapi.repository.SnackRepository;
import br.com.snacksapi.services.PedidoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final ClienteRepository clienteRepository;
    private final SnackRepository snackRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(ClienteRepository clienteRepository,
                             SnackRepository snackRepository,
                             PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.snackRepository = snackRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional
    public Pedido fazerPedido(Long clienteId, Long snackId) {
        Cliente cliente = this.clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("clienteId", clienteId));

        Snack snack = this.snackRepository.findById(snackId)
                .orElseThrow(() -> new SnackNotFoundException("snackId", snackId));

        if(cliente.getDinheiro() < snack.getPrice()) {
            throw new DinheiroInsuficienteException(cliente.getDinheiro(), snack.getPrice());
        }

        cliente.setDinheiro(cliente.getDinheiro() - snack.getPrice());
        this.clienteRepository.save(cliente);
        Pedido pedido = new Pedido(cliente, snack);
        return this.pedidoRepository.save(pedido);

    }

    @Override
    @Transactional
    public void cancelarPedido(Long pedidoId) {
        Pedido pedido = this.pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNotFoundException("pedidoId", pedidoId));
        pedido.setStatus(StatusPedido.CANCELADO);
        Cliente cliente = pedido.getCliente();
        cliente.setDinheiro(cliente.getDinheiro() + pedido.getSnack().getPrice());
        this.clienteRepository.save(cliente);
        this.pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPedido(Long pedidoId) {
        return this.pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNotFoundException("pedidoId", pedidoId));
    }
}
