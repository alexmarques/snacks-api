package br.com.snacksapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Cliente cliente;
    @OneToOne
    private Snack snack;
    private StatusPedido status;

    public Pedido(Cliente cliente, Snack snack) {
        this.cliente = cliente;
        this.snack = snack;
        this.status = StatusPedido.CRIADO;
    }
}
