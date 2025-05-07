package dev.raniery.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Builder
@Schema(description = "Request object for partial movie updates")
public record MoviePatchRequest(
    @Schema(description = "Movie title", example = "The Godfather")
    Optional<String> title,
    
    @Schema(description = "Movie description", example = "The aging patriarch of an organized crime dynasty transfers control to his son")
    Optional<String> description,

    @Schema(description = "Movie release date", example = "24-03-1972")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Optional<LocalDate> releaseDate,

    @Schema(description = "Movie rating (0-10)", example = "9.2")
    Optional<Double> rating,
    
    @Schema(description = "List of category IDs", example = "[1, 2]")
    Optional<List<Long>> categories,
    
    @Schema(description = "List of streaming platform IDs", example = "[1, 3]")
    Optional<List<Long>> streamings) {
}