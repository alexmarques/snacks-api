package br.com.snacksapi.services;

import br.com.snacksapi.model.Cliente;
import br.com.snacksapi.model.Dinheiro;

public interface ClienteService {
    Cliente save(Cliente cliente);
    Cliente adicionarDinheiro(Long clienteId, Dinheiro dinheiro);
}
