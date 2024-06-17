package com.ealmeida.springbootionic.cursomc.resources;

import com.ealmeida.springbootionic.cursomc.domain.Categoria;
import com.ealmeida.springbootionic.cursomc.dto.CategoriaDTO;
import com.ealmeida.springbootionic.cursomc.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.find(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria newCategoria = categoriaService.insert(categoriaService.fromDTO(categoriaDTO));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newCategoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria newCategoria = categoriaService.fromDTO(categoriaDTO);
        newCategoria.setId(id);
        categoriaService.update(newCategoria);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll().stream().map(CategoriaDTO::new).toList());
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
                                                       @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity
                .ok(categoriaService.findPage(page, linesPerPage, orderBy, direction).map(CategoriaDTO::new));
    }

}