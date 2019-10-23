package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums;

import lombok.Getter;

public enum EPermissao {

    BIOT_ADMIN("BIoT Admin"),
    APP_OWNER("Application Owner"),
    USER("User"),
    APPLICATION("APPLICATION");

    @Getter
    private String descricao;

    EPermissao(String descricao) {
        this.descricao = descricao;
    }
}

