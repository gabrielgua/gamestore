package com.gabriel.gamestore.domain.model;

import lombok.Getter;

@Getter
public enum TipoRequisito {

    MINIMOS("Mínimos"), RECOMENDADOS("Recomendados");

    private final String descricao;

    TipoRequisito(String descricao) {
        this.descricao = descricao;
    }
}
