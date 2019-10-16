package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.exception;

import br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception.ValidacaoException;
import lombok.Getter;

public enum ZonaResidencialException {

    INSCRICAO_NAO_ENCONTRADA(new ValidacaoException("A inscrição imobiliária não foi encontrada."));

    @Getter
    private ValidacaoException exception;

    ZonaResidencialException(ValidacaoException exception) {
        this.exception = exception;
    }
}