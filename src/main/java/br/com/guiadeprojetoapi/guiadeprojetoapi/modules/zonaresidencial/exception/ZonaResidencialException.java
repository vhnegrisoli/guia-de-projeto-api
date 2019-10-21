package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.exception;

import br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception.ValidacaoException;
import lombok.Getter;

public enum ZonaResidencialException {

    INSCRICAO_NAO_ENCONTRADA(new ValidacaoException("A inscrição imobiliária não foi encontrada.")),
    ZONA_RESIDENCIAL_NAO_ENCONTRADA(new ValidacaoException("A zona residencial não foi encontrada.")),
    DETALHAMENTO_NAO_ENCONTRADO(new ValidacaoException("O detalhamento não foi encontrado.")),
    AREA_VAZIA(new ValidacaoException("A área deve ser informada para prosseguirmos com os cálculos.")),
    OBRA_NAO_CADASTRADA(new ValidacaoException("A obra não existe.")),
    OBRA_JA_EXISTENTE(new ValidacaoException("Essa obra já está cadastrada."));

    @Getter
    private ValidacaoException exception;

    ZonaResidencialException(ValidacaoException exception) {
        this.exception = exception;
    }
}
