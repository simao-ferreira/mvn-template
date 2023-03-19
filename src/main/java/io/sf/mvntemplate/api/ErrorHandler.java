package io.sf.mvntemplate.api;

import io.sf.mvntemplate.infrastructure.exception.ChuckNorrisResponseException;
import io.sf.mvntemplate.api.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
@ResponseBody
public class ErrorHandler {

    @ExceptionHandler(ChuckNorrisResponseException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public ErrorResponse chuckNorrisException(HttpServletRequest request, ChuckNorrisResponseException exception) {
        return ErrorResponse.builder()
                .url(request.getRequestURL().toString())
                .errorCode(UNPROCESSABLE_ENTITY.value())
                .exception(exception.getMessage())
                .build();
    }
}
