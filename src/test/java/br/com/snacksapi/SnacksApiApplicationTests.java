package br.com.snacksapi;

import br.com.snacksapi.handlers.ValidationErrorResponse;
import br.com.snacksapi.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SnacksApiApplication.class)
class SnacksApiApplicationTests {

    private static final Double DEZ_REAIS = 10.0;
    private static final Double VINTE_REAIS = 20.0;
    private static final Dinheiro DEZ_REAIS_EM_DINHEIRO = new Dinheiro(DEZ_REAIS, DinheiroTipo.NOTA);
    private static final Dinheiro VINTE_REAIS_EM_DINHEIRO = new Dinheiro(VINTE_REAIS, DinheiroTipo.NOTA);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAdicionarDinheiroNaContaDoCliente() {

        Cliente cliente = criarCliente();

        adicionarDinheiro(cliente, DEZ_REAIS_EM_DINHEIRO);
        cliente = buscarClientePorId(cliente.getId());
        assertThat(cliente.getDinheiro()).isEqualTo(DEZ_REAIS);

        adicionarDinheiro(cliente, DEZ_REAIS_EM_DINHEIRO);
        cliente = buscarClientePorId(cliente.getId());
        assertThat(cliente.getDinheiro()).isEqualTo(VINTE_REAIS);
    }


    @Test
    void testDevolverDinheiroParaContaDoClienteAoCancelarPedido() {
        Cliente cliente = criarCliente();

        adicionarDinheiro(cliente, DEZ_REAIS_EM_DINHEIRO);
        Snack snack = buscarSnackAleatorio();

        Pedido pedido = fazerPedido(cliente, snack);
        cancelarPedido(pedido.getId());

        cliente = buscarClientePorId(cliente.getId());
        assertThat(cliente.getDinheiro()).isEqualTo(DEZ_REAIS);
    }

    @Test
    void testDescontarDinheiroDaContaDoClienteAoFazerPedido() {
        Cliente cliente = criarCliente();
        adicionarDinheiro(cliente, DEZ_REAIS_EM_DINHEIRO);
        Snack snack = buscarSnackAleatorio();
        fazerPedido(cliente, snack);
        cliente = buscarClientePorId(cliente.getId());
        assertThat(cliente.getDinheiro()).isEqualTo(DEZ_REAIS - snack.getPrice());
    }

    @Test
    void testCancelarPedido() {
        Cliente cliente = criarCliente();
        adicionarDinheiro(cliente, DEZ_REAIS_EM_DINHEIRO);
        Snack snack = buscarSnackAleatorio();
        Pedido pedido = fazerPedido(cliente, snack);
        cancelarPedido(pedido.getId());
        pedido = buscarPedido(pedido.getId());
        assertThat(pedido.getStatus()).isEqualTo(StatusPedido.CANCELADO);
    }

    @Test
    void testRejeitarPedidoQuandoDinheiroDoClienteEstiverAbaixoDoValorDoSnack() {
        Cliente cliente = criarCliente();
        Snack snack = buscarSnackAleatorio();
        Dinheiro dinheiro = new Dinheiro(snack.getPrice() / 2, DinheiroTipo.NOTA);
        adicionarDinheiro(cliente, dinheiro);
        ResponseEntity<ValidationErrorResponse> response = fazerPedidoError(cliente, snack);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrors().get(0).getMessage()).isEqualTo("dinheiro insuficiente");
    }

    @Test
    void testAdicionarDinheiroInvalido() {

    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente("Joao", null);
        String url = "http://localhost:" + port + "/clientes";
        return this.restTemplate.postForObject(url, cliente, Cliente.class);
    }

    private void adicionarDinheiro(Cliente clienteCriado, Dinheiro dinheiro) {
        String url = "http://localhost:" + port + "/clientes/" + clienteCriado.getId() + "/addMoney";
        this.restTemplate.put(url, dinheiro);
    }

    private Cliente buscarClientePorId(Long clienteId) {
        String url = "http://localhost:" + port + "/clientes/" + clienteId;
        return this.restTemplate.getForObject(url, Cliente.class);
    }

    private Snack buscarSnackAleatorio() {
        String url = "http://localhost:" + port + "/snacks";
        Snack[] snacks = this.restTemplate.getForObject(url, Snack[].class);
        int randomIndex = ThreadLocalRandom.current().nextInt(0, snacks.length - 1);
        return snacks[randomIndex];
    }

    private Pedido fazerPedido(Cliente cliente, Snack snack) {
        String url = "http://localhost:" + port + "/pedidos/" + cliente.getId() + "/" + snack.getId();
        return this.restTemplate.postForObject(url, null, Pedido.class);
    }

    private ResponseEntity<ValidationErrorResponse> fazerPedidoError(Cliente cliente, Snack snack) {
        String url = "http://localhost:" + port + "/pedidos/" + cliente.getId() + "/" + snack.getId();
        return this.restTemplate.postForEntity(url, null, ValidationErrorResponse.class);
    }

    private Pedido buscarPedido(Long pedidoId) {
        String url = "http://localhost:" + port + "/pedidos/" + pedidoId;
        return this.restTemplate.getForObject(url, Pedido.class);
    }

    private void cancelarPedido(Long pedidoId) {
        this.restTemplate.delete("http://localhost:" + port + "/pedidos/" + pedidoId);
    }
}
