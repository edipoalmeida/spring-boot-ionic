package com.ealmeida.springbootionic.cursomc.repositories;

import com.ealmeida.springbootionic.cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
