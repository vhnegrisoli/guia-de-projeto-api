package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.enums;

import lombok.Getter;

public enum EClassificacaoResidencial {

    RA("Residencial Agrupada"),
    RMHA("Residencial Multifamiliar Horizontal Agrupada"),
    RMHV("Residencial Multifamiliar Horizontal em Vilas"),
    RMHI("Residencial Multifamiliar Horizontal Isolada"),
    RMS("Residencial Multifamiliar Sobreposta"),
    RMV("Residencial Multifamiliar Vertical"),
    RU("Residencial Unifamiliar"),
    RMHA_S("Residencial Multifamiliar Horizontal Agrupada Sobreposta");

    @Getter
    private String descricao;
    EClassificacaoResidencial(String descricao) {
        this.descricao = descricao;
    }
}
