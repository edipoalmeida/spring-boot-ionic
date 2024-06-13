package com.ealmeida.springbootionic.cursomc.domain;

import com.ealmeida.springbootionic.cursomc.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;

import java.io.Serial;

@Entity
public class PagamentoComCartao extends Pagamento {

    @Serial
    private static final long serialVersionUID = 804667565676899817L;
    private Integer numeroParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
        super(id, estado, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}
