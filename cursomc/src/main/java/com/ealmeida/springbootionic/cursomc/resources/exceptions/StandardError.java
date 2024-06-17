package com.ealmeida.springbootionic.cursomc.resources.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = -612590505287894451L;
    private Integer status; 
    private String cause;
    private LocalDateTime time;

    public StandardError(Integer status, String cause, LocalDateTime time) {
        this.status = status;
        this.cause = cause;
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
