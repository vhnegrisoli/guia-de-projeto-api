package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.moduloaplicativo;

import lombok.Getter;

public enum EModulo {

    ZONA_RESIDENCIAL("Zona Residencial"),
    CORPO_BOMBEIROS("Corpo de Bombeiros"),
    AGUA_ESGOTO("Água e Esgoto");

    @Getter
    private String descricao;
    EModulo(String descricao) {
        this.descricao = descricao;
    }
}
