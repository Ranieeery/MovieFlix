package dev.raniery.movieflix.controller.request;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@Builder
public record MovieRequest(
    String title,
    String description,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate releaseDate,

    Double rating,
    List<Long> categories,
    List<Long> streamings) {
}
