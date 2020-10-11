package br.com.snacksapi.validators;

import br.com.snacksapi.model.Dinheiro;
import br.com.snacksapi.model.DinheiroTipo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class DinheiroValidator implements ConstraintValidator<ValidDinheiro, Dinheiro> {

    private static final Double[] MOEDA_RANGE = new Double[]{1.0, 5.0, 10.0, 25.0, 50.0};
    private static final Double[] NOTA_RANGE = new Double[]{2.0, 5.0, 10.0};

    @Override
    public boolean isValid(Dinheiro dinheiro, ConstraintValidatorContext constraintValidatorContext) {

        if(dinheiro.getTipo().equals(DinheiroTipo.MOEDA)) {
            return Arrays.binarySearch(MOEDA_RANGE, dinheiro.getQuantidade()) != -1;
        }

        if(dinheiro.getTipo().equals(DinheiroTipo.NOTA)) {
            return Arrays.binarySearch(NOTA_RANGE, dinheiro.getQuantidade()) != -1;
        }
        
        return true;
    }
}
