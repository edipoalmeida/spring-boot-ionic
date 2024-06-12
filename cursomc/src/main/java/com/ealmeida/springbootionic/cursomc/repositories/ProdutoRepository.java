package com.ealmeida.springbootionic.cursomc.repositories;

import com.ealmeida.springbootionic.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
