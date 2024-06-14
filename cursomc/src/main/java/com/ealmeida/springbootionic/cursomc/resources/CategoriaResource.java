package com.ealmeida.springbootionic.cursomc.resources;

import com.ealmeida.springbootionic.cursomc.domain.Categoria;
import com.ealmeida.springbootionic.cursomc.repositories.CategoriaRepository;
import com.ealmeida.springbootionic.cursomc.services.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> listar(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.buscar(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Categoria newCategoria) {
        newCategoria = categoriaService.insertNew(newCategoria);
        URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(newCategoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
