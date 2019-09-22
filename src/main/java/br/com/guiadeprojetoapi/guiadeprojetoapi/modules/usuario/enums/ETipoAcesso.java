package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums;

import lombok.Getter;

public enum ETipoAcesso {

    B2VN("B2VN"),
    ESTUDANTE("Estudante"),
    ENGENHEIRO_CIVIL("Engenheiro Civil"),
    TECNICO_EDIFICACAO("Técnico de Edificação"),
    ARQUITETO("Arquiteto");

    @Getter
    private String descricao;

    ETipoAcesso(String descricao) {
        this.descricao = descricao;
    }
}
