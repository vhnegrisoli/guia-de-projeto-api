package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.exception;

import br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception.ValidacaoException;
import lombok.Getter;

public enum ClienteException {

    CLIENTE_NAO_ENCONTRADO(new ValidacaoException("O cliente não foi encontrado.")),
    CLIENTE_CPF_JA_CADASTRADO(new ValidacaoException("Esse CPF já está cadastrado para um cliente.")),
    CLIENTE_NOME_JA_CADASTRADO(new ValidacaoException("Esse nome já está cadastrado para um cliente."));
    @Getter
    private ValidacaoException exception;

    ClienteException(ValidacaoException exception) {
        this.exception = exception;
    }
}
