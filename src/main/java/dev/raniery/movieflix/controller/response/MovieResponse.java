package dev.raniery.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
@Schema(description = "Movie information response")
public record MovieResponse(
    @Schema(description = "Unique identifier", example = "1")
    Long id,

    @Schema(description = "Movie title", example = "The Avengers")
    String title,

    @Schema(description = "Movie description", example = "A superhero film based on Marvel Comics")
    String description,

    @Schema(description = "Original release date", example = "25-04-2012")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate releaseDate,

    @Schema(description = "Movie rating on a scale from 0 to 10", example = "8.5")
    Double rating,

    @Schema(description = "Categories the movie belongs to")
    List<CategoryResponse> categories,

    @Schema(description = "Streaming platforms where the movie is available")
    List<StreamingResponse> streamings) {
}
