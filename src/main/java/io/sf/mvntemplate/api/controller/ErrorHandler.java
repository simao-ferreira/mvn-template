package io.sf.mvntemplate.api.controller;

import io.sf.mvntemplate.domain.service.exception.ChuckNorrisResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ErrorHandler {

    @ExceptionHandler(ChuckNorrisResponseException.class)
    public ResponseEntity customException(ChuckNorrisResponseException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception);
    }
}
