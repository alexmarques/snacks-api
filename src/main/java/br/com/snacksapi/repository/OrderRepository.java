package br.com.snacksapi.repository;

import br.com.snacksapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Pedido, Long> {
}
