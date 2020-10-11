package br.com.snacksapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Troco {
    private Cliente cliente;
    private Snack snack;
    private Double quantidade;
}
