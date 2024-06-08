package com.ealmeida.springbootionic.cursomc.resources;

import com.ealmeida.springbootionic.cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {

        Categoria c1 = new Categoria(1, "Informática");
        Categoria c2 = new Categoria(2, "Escritório");

        List<Categoria> categorias = Arrays.asList(c1, c2);

        return categorias;
    }
}
