package dev.raniery.movieflix.controller.response;

import lombok.Builder;

@Builder
public record MovieResponse(Long id, String title) {
}
