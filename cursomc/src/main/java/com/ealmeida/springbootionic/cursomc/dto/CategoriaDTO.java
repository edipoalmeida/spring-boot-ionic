package com.ealmeida.springbootionic.cursomc.dto;

import com.ealmeida.springbootionic.cursomc.domain.Categoria;

import java.io.Serial;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4614464389929666617L;

    private Integer id;
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        id = categoria.getId();
        nome = categoria.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
