package com.ealmeida.springbootionic.cursomc.services;

import com.ealmeida.springbootionic.cursomc.domain.Cidade;
import com.ealmeida.springbootionic.cursomc.domain.Cliente;
import com.ealmeida.springbootionic.cursomc.domain.Endereco;
import com.ealmeida.springbootionic.cursomc.domain.enums.TipoCliente;
import com.ealmeida.springbootionic.cursomc.dto.ClienteDTO;
import com.ealmeida.springbootionic.cursomc.dto.ClienteNewDTO;
import com.ealmeida.springbootionic.cursomc.repositories.ClienteRepository;
import com.ealmeida.springbootionic.cursomc.services.exceptions.DataIntegrityException;
import com.ealmeida.springbootionic.cursomc.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final CidadeService cidadeService;

    private final EnderecoService enderecoService;

    public ClienteService(ClienteRepository clienteRepository, CidadeService cidadeService, EnderecoService enderecoService) {
        this.clienteRepository = clienteRepository;
        this.cidadeService = cidadeService;
        this.enderecoService = enderecoService;
    }

    public Cliente find(Integer id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! ID:" +id+
                ", Tipo:" + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente newCliente) {
        newCliente.setId(null);
        enderecoService.save(newCliente.getEnderecos());
        Cliente cliente = clienteRepository.save(newCliente);
        return cliente;
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

    private void updateData(Cliente newObj, Cliente cliente) {
        newObj.setNome(cliente.getNome());
        newObj.setEmail(cliente.getEmail());
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
        Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipo()));
        Cidade cidade = cidadeService.findById(clienteNewDTO.getCidadeId());
        Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cli, cidade);
        cli.getEnderecos().add(endereco);
        cli.getTelefones().add(clienteNewDTO.getTelefone1());
        if (clienteNewDTO.getTelefone2() != null)
            cli.getTelefones().add(clienteNewDTO.getTelefone2());
        if (clienteNewDTO.getTelefone3() != null)
            cli.getTelefones().add(clienteNewDTO.getTelefone3());

        return cli;
    }
}
