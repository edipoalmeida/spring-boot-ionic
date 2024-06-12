package com.ealmeida.springbootionic.cursomc.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Estado implements Serializable {

    @Serial
    private static final long serialVersionUID = 6945183668805403384L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;

    public Estado() {
    }

    public Estado(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(getId(), estado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
