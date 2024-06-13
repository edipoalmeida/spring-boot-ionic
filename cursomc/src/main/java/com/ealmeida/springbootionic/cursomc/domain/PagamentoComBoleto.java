package com.ealmeida.springbootionic.cursomc.domain;

import com.ealmeida.springbootionic.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.time.LocalDateTime;

@Entity
public class PagamentoComBoleto extends Pagamento{

    @Serial
    private static final long serialVersionUID = -3879209583967963447L;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, LocalDateTime dataVencimento, LocalDateTime dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
