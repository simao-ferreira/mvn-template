package io.sf.mvntemplate.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@Builder
@AllArgsConstructor
public class JokeResponse {

    @NonNull
    @Schema(description = "Joke", requiredMode = REQUIRED)
    String joke;
}
