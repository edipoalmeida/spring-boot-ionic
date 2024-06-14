package com.ealmeida.springbootionic.cursomc.services.exceptions;

import java.io.Serial;

public class DataIntegrityException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7644150645680648221L;

    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable e) {
        super(msg, e);
    }
}
