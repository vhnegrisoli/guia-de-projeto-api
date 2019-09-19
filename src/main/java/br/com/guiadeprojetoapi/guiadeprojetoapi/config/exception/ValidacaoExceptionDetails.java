package br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception;

import lombok.Data;

@Data
public class ValidacaoExceptionDetails {

    private String title;
    private int status;
    private String details;
    private long timestamp;

}
