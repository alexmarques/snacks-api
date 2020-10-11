package br.com.snacksapi.validators;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DinheiroValidator.class)
@Documented
public @interface ValidDinheiro {

}
