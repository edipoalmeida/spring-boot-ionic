package com.ealmeida.springbootionic.cursomc.services;

import com.ealmeida.springbootionic.cursomc.domain.Cliente;
import com.ealmeida.springbootionic.cursomc.dto.ClienteDTO;
import com.ealmeida.springbootionic.cursomc.repositories.ClienteRepository;
import com.ealmeida.springbootionic.cursomc.services.exceptions.DataIntegrityException;
import com.ealmeida.springbootionic.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente find(Integer id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! ID:" +id+
                ", Tipo:" + Cliente.class.getName()));
    }


    public Cliente update(Cliente cliente) {
        Cliente newObj = find(cliente.getId());
        updateData(newObj, cliente);
        return clienteRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }


    private void updateData(Cliente newObj, Cliente cliente) {
        newObj.setNome(cliente.getNome());
        newObj.setEmail(cliente.getEmail());
    }

}
