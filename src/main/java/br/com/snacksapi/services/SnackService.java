package br.com.snacksapi.services;

import br.com.snacksapi.model.Snack;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SnackService {
    List<Snack> findAll();
}
