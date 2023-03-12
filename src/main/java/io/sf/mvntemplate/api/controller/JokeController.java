package io.sf.mvntemplate.api.controller;


import io.sf.mvntemplate.domain.service.JokeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/v0")
@Tag(name = "Joke Controller", description = "Retrieve a Chuck Norris Fact")
public class JokeController {

    @Resource
    private JokeService service;

    @Operation(method = "GET", summary = "Get joke")
    @ApiResponse(responseCode = "200", description = "Successful return of daily available currencies")
    @GetMapping(value = "/joke-endpoint", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public String endpoint() throws IOException {
        log.trace("Request trace");

        return service.getJoke();
    }

}

