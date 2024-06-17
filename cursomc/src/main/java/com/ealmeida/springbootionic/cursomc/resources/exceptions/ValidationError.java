package com.ealmeida.springbootionic.cursomc.resources.exceptions;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    @Serial
    private static final long serialVersionUID = 5236817246190948980L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status,
                           String cause,
                           LocalDateTime time) {
        super(status, cause, time);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
