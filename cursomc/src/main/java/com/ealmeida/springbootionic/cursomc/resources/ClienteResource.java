package com.ealmeida.springbootionic.cursomc.resources;

import com.ealmeida.springbootionic.cursomc.domain.Cliente;
import com.ealmeida.springbootionic.cursomc.dto.ClienteDTO;
import com.ealmeida.springbootionic.cursomc.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.find(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente newCliente = clienteService.fromDTO(clienteDTO);
        newCliente.setId(id);
        clienteService.update(newCliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok(clienteService.findAll().stream().map(ClienteDTO::new).toList());
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
                                                       @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity
                .ok(clienteService.findPage(page, linesPerPage, orderBy, direction).map(ClienteDTO::new));
    }
}
