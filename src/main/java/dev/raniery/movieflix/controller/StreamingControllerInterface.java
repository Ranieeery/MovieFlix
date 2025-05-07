package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.StreamingRequest;
import dev.raniery.movieflix.controller.response.StreamingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Streaming", description = "CRUD operations for streaming platforms")
public interface StreamingControllerInterface {

    @Operation(
        summary = "Create streaming platform",
        description = "Creates a new streaming platform in the system",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Streaming platform created successfully",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid streaming platform data provided",
            content = @Content
        )
    })
    ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest request);

    @Operation(
        summary = "List all streaming platforms",
        description = "Retrieves a list of all streaming platforms available in the system",
        security = @SecurityRequirement(name = "bearerAuth")

    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved streaming platforms",
        content = @Content(schema = @Schema(implementation = StreamingResponse.class))
    )
    ResponseEntity<List<StreamingResponse>> getAll();

    @Operation(
        summary = "Get streaming platform by ID",
        description = "Retrieves a specific streaming platform by its ID",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Streaming platform found",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Streaming platform not found",
            content = @Content
        )
    })
    ResponseEntity<StreamingResponse> getById(@PathVariable Long id);

    @Operation(
        summary = "Update streaming platform",
        description = "Updates an existing streaming platform's information",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Streaming platform updated successfully",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid streaming platform data provided",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Streaming platform not found",
            content = @Content
        )
    })
    ResponseEntity<StreamingResponse> update(@PathVariable Long id, @Valid @RequestBody StreamingRequest request);

    @Operation(
        summary = "Delete streaming platform",
        description = "Removes a streaming platform from the system",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Streaming platform deleted successfully",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Streaming platform not found",
            content = @Content
        )
    })
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}
