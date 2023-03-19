package io.sf.mvntemplate.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    @Schema(description = "Url request", requiredMode = NOT_REQUIRED)
    public String url;

    @Schema(description = "Error code", requiredMode = REQUIRED)
    public int errorCode;

    @NonNull
    @Schema(description = "Error message", requiredMode = REQUIRED)
    public String exception;

}
