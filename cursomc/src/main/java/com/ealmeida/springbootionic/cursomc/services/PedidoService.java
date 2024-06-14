package com.ealmeida.springbootionic.cursomc.services;

import com.ealmeida.springbootionic.cursomc.domain.Pedido;
import com.ealmeida.springbootionic.cursomc.repositories.PedidoRepository;
import com.ealmeida.springbootionic.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido find(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! ID:" +id+
                ", Tipo:" + Pedido.class.getName()));
    }
}
