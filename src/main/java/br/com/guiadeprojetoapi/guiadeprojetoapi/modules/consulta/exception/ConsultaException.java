package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.exception;

import br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception.ValidacaoException;
import lombok.Getter;

public enum ConsultaException {

    CONSULTA_NAO_ENCONTRADA(new ValidacaoException("A consulta não foi encontrada.")),
    CONSULTA_VAZIA(new ValidacaoException("Você não possui consultas.")),
    CONSULTA_LIMITE_ATINGIDO(new ValidacaoException("Você atingiu o limite de 10 consultas."));

    @Getter
    private ValidacaoException exception;

    ConsultaException(ValidacaoException exception) {
        this.exception = exception;
    }
}
