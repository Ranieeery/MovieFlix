package dev.raniery.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Streaming platform information")
public record StreamingResponse(
    @Schema(description = "Unique identifier", example = "1")
    Long id,

    @Schema(description = "Streaming platform name", example = "Netflix")
    String title) {
}
