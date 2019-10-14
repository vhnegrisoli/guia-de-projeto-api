package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.exception;

import br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception.ValidacaoException;
import lombok.Getter;

public enum EnderecoException {

    CIDADE_NAO_ENCONTRADA(new ValidacaoException("Cidade não encontrada.")),
    ENDERECO_NAO_ENCONTADO(new ValidacaoException("Endereço não encontrado."));

    @Getter
    private ValidacaoException exception;

    EnderecoException(ValidacaoException exception) {
        this.exception = exception;
    }
}
