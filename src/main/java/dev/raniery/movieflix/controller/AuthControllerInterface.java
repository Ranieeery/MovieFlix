package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.LoginRequest;
import dev.raniery.movieflix.controller.request.UserRequest;
import dev.raniery.movieflix.controller.response.LoginResponse;
import dev.raniery.movieflix.controller.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication", description = "Endpoints for user registration and authentication")
public interface AuthControllerInterface {

    @Operation(
        summary = "Register new user",
        description = "Creates a new user account in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "User registered successfully",
            content = @Content(schema = @Schema(implementation = UserResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid user data provided",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Email already exists",
            content = @Content
        )
    })
    ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest);

    @Operation(
        summary = "Authenticate user",
        description = "Validates user credentials and returns access token"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication successful",
            content = @Content(schema = @Schema(implementation = LoginResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request data",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid username or password",
            content = @Content
        )
    })
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);
}
