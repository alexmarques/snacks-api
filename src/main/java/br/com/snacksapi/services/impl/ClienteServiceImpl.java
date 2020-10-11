package br.com.snacksapi.services.impl;

import br.com.snacksapi.exceptions.ClienteNotFoundException;
import br.com.snacksapi.model.Cliente;
import br.com.snacksapi.model.Dinheiro;
import br.com.snacksapi.repository.ClienteRepository;
import br.com.snacksapi.services.ClienteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return this.repository.save(cliente);
    }

    @Override
    public Cliente adicionarDinheiro(Long clienteId, Dinheiro dinheiro) {
        Cliente clienteFromRepo = this.repository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("clienteId", clienteId));
        Double dinheiroCliente = Optional.ofNullable(clienteFromRepo.getDinheiro()).orElse(0.0);
        clienteFromRepo.setDinheiro(dinheiroCliente + dinheiro.getQuantidade());
        return this.repository.save(clienteFromRepo);
    }
}
