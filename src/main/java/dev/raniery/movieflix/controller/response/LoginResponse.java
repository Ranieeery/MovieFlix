package dev.raniery.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response containing JWT token")
public record LoginResponse(
    @Schema(description = "JWT authentication token to be used for secured endpoints", 
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String token) {
}
