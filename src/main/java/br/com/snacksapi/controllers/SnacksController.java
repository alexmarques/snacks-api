package br.com.snacksapi.controllers;

import br.com.snacksapi.services.SnackService;
import br.com.snacksapi.model.Snack;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/snacks")
public class SnacksController {

    private final SnackService snackService;

    public SnacksController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping
    public List<Snack> findAll() {
        return this.snackService.findAll();
    }


}
