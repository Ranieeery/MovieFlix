package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.MoviePatchRequest;
import dev.raniery.movieflix.controller.request.MovieRequest;
import dev.raniery.movieflix.controller.response.MovieResponse;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Movie", description = "CRUD operations in movie controller")
public interface MovieControllerInterface {

    @Operation(
        summary = "Create movie",
        description = "Creates a new movie with associated categories and streaming platforms",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Movie created successfully",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid movie data provided",
            content = @Content
        )
    })
    ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request);

    @Operation(
        summary = "List all movies",
        description = "Retrieves a list of all movies available in the system",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved movies",
        content = @Content(schema = @Schema(implementation = MovieResponse.class))
    )
    ResponseEntity<List<MovieResponse>> getAll();

    @Operation(
        summary = "Get movie by ID",
        description = "Retrieves a specific movie by its ID",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Movie found",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Movie not found",
            content = @Content
        )
    })
    ResponseEntity<MovieResponse> getById(@PathVariable Long id);

    @Operation(
        summary = "Search movies by category",
        description = "Finds all movies that belong to a specific category",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Search completed successfully",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
        )
    })
    ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category);

    @Operation(
        summary = "Update movie",
        description = "Updates an existing movie's information including categories and streaming platforms",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Movie updated successfully",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid movie data provided",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Movie not found",
            content = @Content
        )
    })
    ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MoviePatchRequest request);

    @Operation(
        summary = "Delete movie",
        description = "Removes a movie from the system",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Movie deleted successfully",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Movie not found",
            content = @Content
        )
    })
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}
