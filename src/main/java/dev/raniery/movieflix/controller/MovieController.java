package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.MoviePatchRequest;
import dev.raniery.movieflix.controller.request.MovieRequest;
import dev.raniery.movieflix.controller.response.MovieResponse;
import dev.raniery.movieflix.entity.Movie;
import dev.raniery.movieflix.mapper.MovieMapper;
import dev.raniery.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieflix/movies")
@Tag(name = "Movie", description = "CRUD operations in movie controller")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
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
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(request));

        return ResponseEntity
            .created(URI.create("/movieflix/movies/" + savedMovie.getId()))
            .body(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
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
    public ResponseEntity<List<MovieResponse>> getAll() {
        List<MovieResponse> movies = movieService
            .findAll()
            .stream()
            .map(MovieMapper::toMovieResponse)
            .toList();

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id) {
        return movieService
            .findById(id)
            .map(c -> ResponseEntity.ok(MovieMapper.toMovieResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
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
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category) {
        List<MovieResponse> list = movieService
            .findByCategory(category)
            .stream()
            .map(MovieMapper::toMovieResponse)
            .toList();

        return ResponseEntity.ok(list);
    }

    @PatchMapping("/{id}")
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
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MoviePatchRequest request) {

        return movieService
            .update(id, MovieMapper.toPartialMovie(request))
            .map(c -> ResponseEntity.ok(MovieMapper.toMovieResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
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
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Movie> optionalMovie = movieService.findById(id);

        if (optionalMovie.isPresent()) {
            movieService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
