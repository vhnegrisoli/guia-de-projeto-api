package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.enums;

import lombok.Getter;

public enum EZonaResidencial {

    ZR1("Zona Residencial 1"),
    ZR3("Zona Residencial 3"),
    ZR2("Zona Residencial 2"),
    ZR4("Zona Residencial 4"),
    ZR5("Zona Residencial 5"),
    ZR6("Zona Residencial 6"),
    ZR7("Zona Residencial 7"),
    ZR8("Zona Residencial 8"),
    ZR9("Zona Residencial 9");

    @Getter
    private String descricao;
    EZonaResidencial(String descricao) {
        this.descricao = descricao;
    }
}
