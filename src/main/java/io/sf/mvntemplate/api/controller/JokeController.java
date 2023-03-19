package io.sf.mvntemplate.api.controller;

import io.sf.mvntemplate.api.model.ErrorResponse;
import io.sf.mvntemplate.api.model.JokeResponse;
import io.sf.mvntemplate.domain.service.JokeCategory;
import io.sf.mvntemplate.domain.service.JokeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/v0")
@Tag(name = "Jokes", description = "Retrieve a Chuck Norris Fact")
public class JokeController {

    @Resource
    private JokeService service;

    @Operation(method = "GET", summary = "Get joke", description = "Gets a joke from ChuckNorris Api")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successful return a joke",
                    content = @Content(schema = @Schema(implementation = JokeResponse.class))
            ),
            @ApiResponse(
                    responseCode = "422", description = "Error while processing response",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping(value = "/joke-endpoint", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public JokeResponse jokeEndpoint() {
        log.trace("A joke was requested");

        return service.getJoke();
    }

    @Operation(method = "GET", summary = "Get joke", description = "Gets a joke from ChuckNorris Api")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successful return a joke",
                    content = @Content(schema = @Schema(implementation = JokeResponse.class))
            ),
            @ApiResponse(
                    responseCode = "422", description = "Error while processing response",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping(value = "/category-joke-endpoint", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public JokeResponse categoryJokeEndpoint(
            @Parameter(name = "category", description = "Joke category")
            @RequestParam(value = "category", required = true) JokeCategory category
    ) {
        log.trace("A joke was requested for category {}", category);

        return service.getCategoryJoke(category);
    }

}

