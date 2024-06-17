package com.ealmeida.springbootionic.cursomc.dto;

import com.ealmeida.springbootionic.cursomc.domain.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public class ClienteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7382999044515899972L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 120, message = "O nome deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "Preenchimento obrigatório") @Size(min = 5, max = 80, message = "O nome deve ser entre 5 e 80 caracteres") String getNome() {
        return nome;
    }

    public void setNome(@NotEmpty(message = "Preenchimento obrigatório") @Size(min = 5, max = 80, message = "O nome deve ser entre 5 e 80 caracteres") String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
