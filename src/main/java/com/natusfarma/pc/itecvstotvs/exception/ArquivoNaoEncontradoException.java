package com.natusfarma.pc.itecvstotvs.exception;

import java.io.File;

public class ArquivoNaoEncontradoException extends RuntimeException{

    public ArquivoNaoEncontradoException(Throwable cause) {
        super(cause);
    }

    public ArquivoNaoEncontradoException(File file, Throwable cause) {
        super(String.format("O arquivo %s não pode ser criado. Causa %s ",file.getName()), cause);
    }
}
