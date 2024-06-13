package com.ealmeida.springbootionic.cursomc.domain.enums;

import java.util.Arrays;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa física"),
    PESSOAJURIDICA(2, "Pessoa jurídica");

    private final int codigo;
    private final String descricao;

    TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) return null;
        return Arrays.stream(values()).filter(tipoCliente -> tipoCliente.getCodigo() == cod)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + cod));
    }


}
