package com.ealmeida.springbootionic.cursomc.repositories;

import com.ealmeida.springbootionic.cursomc.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
