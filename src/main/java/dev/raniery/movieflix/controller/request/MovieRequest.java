package dev.raniery.movieflix.controller.request;

import lombok.Builder;

@Builder
public record MovieRequest(String title) {
}
