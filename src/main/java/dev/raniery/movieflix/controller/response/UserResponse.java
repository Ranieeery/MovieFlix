package dev.raniery.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
@Schema(description = "User account information")
public record UserResponse(
    @Schema(description = "Unique user identifier")
    UUID id,
    
    @Schema(description = "User's name", example = "Jack Black")
    String name,
    
    @Schema(description = "User's email address", example = "jblack@example.com")
    String email) {
}
