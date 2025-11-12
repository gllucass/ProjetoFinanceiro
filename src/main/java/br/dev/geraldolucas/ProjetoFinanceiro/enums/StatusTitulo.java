package br.dev.geraldolucas.ProjetoFinanceiro.enums;

import lombok.Getter;

@Getter
public enum StatusTitulo {
    PENDENTE("Pendente"),
    PAGO("Pago");

    private final String descricao;

    StatusTitulo(String descricao) {
        this.descricao = descricao;
    }
}
