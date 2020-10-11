package br.com.snacksapi.controllers;

import br.com.snacksapi.model.Cliente;
import br.com.snacksapi.model.Dinheiro;
import br.com.snacksapi.services.ClienteService;
import br.com.snacksapi.validators.ValidDinheiro;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente) {
        return this.clienteService.save(cliente);
    }

    @PutMapping("/{clienteId}/addMoney")
    public Cliente adicionarDinheiro(@PathVariable Long clienteId,
                                     @ValidDinheiro @RequestBody Dinheiro dinheiro) {
        return this.clienteService.adicionarDinheiro(clienteId, dinheiro);
    }
}
