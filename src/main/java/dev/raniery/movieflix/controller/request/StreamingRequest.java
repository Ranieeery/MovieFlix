package dev.raniery.movieflix.controller.request;

import lombok.Builder;

@Builder
public record StreamingRequest(String title) {
}
