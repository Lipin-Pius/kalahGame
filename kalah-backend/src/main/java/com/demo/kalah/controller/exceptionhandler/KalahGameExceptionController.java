package com.demo.kalah.controller.exceptionhandler;

import com.demo.kalah.exception.NoSuchGameExistsException;
import com.demo.kalah.exception.SowingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handlers
 */
@RestControllerAdvice
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class KalahGameExceptionController {

    @ExceptionHandler(value = NoSuchGameExistsException.class)
    public String noSuchGameExistsExceptionHandler(final NoSuchGameExistsException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = SowingException.class)
    public String sowingExceptionHandler(final SowingException exception) {
        return exception.getMessage();
    }
}
