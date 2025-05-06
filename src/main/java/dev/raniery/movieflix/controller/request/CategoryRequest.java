package dev.raniery.movieflix.controller.request;

import lombok.Builder;

@Builder
public record CategoryRequest(String name) {
}
