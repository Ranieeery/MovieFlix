package dev.raniery.movieflix.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequest(
    @NotBlank(message = "Streaming title can't be blank or null")
    String name)  {
}
