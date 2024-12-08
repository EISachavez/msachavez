package com.delph.msachavez.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotProcessable extends RuntimeException {

    public NotProcessable(String msg) {
        super(msg);
    }

}
