package com.ealmeida.springbootionic.cursomc.resources.exceptions;

import java.time.LocalDateTime;

public record StandardError(Integer status, String cause, LocalDateTime time) {
}
