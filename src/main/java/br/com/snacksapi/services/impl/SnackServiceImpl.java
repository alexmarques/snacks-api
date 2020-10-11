package br.com.snacksapi.services.impl;

import br.com.snacksapi.model.Snack;
import br.com.snacksapi.repository.SnackRepository;
import br.com.snacksapi.services.SnackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackServiceImpl implements SnackService {
    private final SnackRepository repository;

    public SnackServiceImpl(SnackRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Snack> findAll() {
        return this.repository.findAll();
    }
}
