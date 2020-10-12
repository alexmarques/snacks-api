package br.com.snacksapi.controllers;

import br.com.snacksapi.model.Cliente;
import br.com.snacksapi.repository.ClienteRepository;
import br.com.snacksapi.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClienteControllerTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    void save() {
        Cliente cliente = new Cliente();
        cliente.setName("Alex");
        Cliente savedCliente = clienteRepository.save(cliente);
        assertThat(savedCliente).isNotNull();
        assertThat(savedCliente.getId()).isNotNull();
        assertThat(savedCliente.getDinheiro()).isNull();
    }

    @Test
    void adicionarDinheiro() {
    }
}