package br.com.snacksapi;

import br.com.snacksapi.model.Snack;
import br.com.snacksapi.repository.SnackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Iterator;

@SpringBootApplication
public class SnacksApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnacksApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner createSnacks(final SnackRepository snackRepository) {
        return args -> {
            Snack chocolate = new Snack("Chocolate", 2.35);
            Snack sanduiche = new Snack("Sanduíche", 7.50);
            Snack refrigerante = new Snack("Refrigerante", 5.15);
            Snack biscoito = new Snack("Biscoito", 2.90);
            snackRepository.saveAll(Arrays.asList(chocolate, sanduiche, refrigerante, biscoito));
        };
    }

}
