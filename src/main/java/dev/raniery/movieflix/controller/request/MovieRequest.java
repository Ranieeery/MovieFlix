package dev.raniery.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(
    @NotBlank(message = "Movie title can't be blank or null")
    String title,

    String description,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate releaseDate,

    // @DecimalMin(value = "0.0", message = "Rating must be between 0 and 10")
    // @DecimalMax(value = "10.0", message = "Rating must be between 0 and 10")
    Double rating,

    @NotEmpty(message = "At least one category must be provided")
    List<Long> categories,

    @NotEmpty(message = "At least one streaming platform must be provided")
    List<Long> streamings) {
}
