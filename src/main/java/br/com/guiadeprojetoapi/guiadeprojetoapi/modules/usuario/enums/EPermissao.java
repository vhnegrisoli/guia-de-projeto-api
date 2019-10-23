package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums;

import lombok.Getter;

public enum EPermissao {

    USER("Usuário"),
    ADMIN("Administrador"),
    APPLICATION("APPLICATION");

    @Getter
    private String descricao;

    EPermissao(String descricao) {
        this.descricao = descricao;
    }
}

