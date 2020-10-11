package br.com.snacksapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dinheiro {
    private Double quantidade;
    private DinheiroTipo tipo;
}
