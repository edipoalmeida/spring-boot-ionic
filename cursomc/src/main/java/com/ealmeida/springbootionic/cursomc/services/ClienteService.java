package com.ealmeida.springbootionic.cursomc.services;

import com.ealmeida.springbootionic.cursomc.domain.Cliente;
import com.ealmeida.springbootionic.cursomc.repositories.ClienteRepository;
import com.ealmeida.springbootionic.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository categoriaRepository;

    public ClienteService(ClienteRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Cliente buscar(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! ID:" +id+
                ", Tipo:" + Cliente.class.getName()));
    }
}
