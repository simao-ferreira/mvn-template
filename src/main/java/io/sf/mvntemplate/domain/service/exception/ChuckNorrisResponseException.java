package io.sf.mvntemplate.domain.service.exception;

import okhttp3.Response;

import static java.lang.String.format;

public class ChuckNorrisResponseException extends RuntimeException {

    public ChuckNorrisResponseException(Exception ex) {
        super(format("Failed to get joke with %s", ex));
    }

    public ChuckNorrisResponseException(Response response) {
        super(format("Failed to get joke with %s", response));
    }
}
