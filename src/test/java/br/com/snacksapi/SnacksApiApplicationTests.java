package br.com.snacksapi;

import br.com.snacksapi.model.Cliente;
import br.com.snacksapi.model.Dinheiro;
import br.com.snacksapi.model.DinheiroTipo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SnacksApiApplication.class)
class SnacksApiApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
        Cliente cliente = new Cliente();
        cliente.setName("Alex");
        ResponseEntity<Cliente> response =
                this.restTemplate
                        .postForEntity("http://localhost:" + port + "/clientes", cliente, Cliente.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getDinheiro()).isNull();
        Cliente clienteCriado = response.getBody();
        Dinheiro dinheiro = new Dinheiro();
        dinheiro.setQuantidade(10.0);
        dinheiro.setTipo(DinheiroTipo.NOTA);
        this.restTemplate.put("http://localhost:" + port + "/clientes/" + clienteCriado.getId() + "/addMoney", dinheiro);

        response = this.restTemplate.getForEntity("http://localhost:" + port + "/clientes/" + clienteCriado.getId(), Cliente.class);
        assertThat(response.getBody().getDinheiro()).isEqualTo(10.0);
        this.restTemplate.put("http://localhost:" + port + "/clientes/" + clienteCriado.getId() + "/addMoney", dinheiro);
        assertThat(response.getBody().getDinheiro()).isEqualTo(20.0);
    }

}
