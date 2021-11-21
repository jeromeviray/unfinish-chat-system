package com.chat.v1.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundMessageException extends RuntimeException{
    public NotFoundMessageException(String message) {
        super(message);
    }
}
