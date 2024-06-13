package com.ealmeida.springbootionic.cursomc.domain.enums;

import java.util.Arrays;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private final int codigo;
    private final String descricao;

    EstadoPagamento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer cod) {
        if (cod == null) return null;
        return Arrays.stream(values()).filter(estadoPagamento -> estadoPagamento.getCodigo() == cod)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + cod));
    }
}
