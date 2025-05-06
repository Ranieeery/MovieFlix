package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.MovieRequest;
import dev.raniery.movieflix.controller.response.MovieResponse;
import dev.raniery.movieflix.entity.Movie;
import dev.raniery.movieflix.mapper.MovieMapper;
import dev.raniery.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieflix/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAll() {
        List<MovieResponse> movies = movieService
            .findAll()
            .stream()
            .map(MovieMapper::toMovieResponse)
            .toList();

        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(request));

        return ResponseEntity
            .created(URI.create("/movieflix/movies/" + savedMovie.getId()))
            .body(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id) {
        return movieService
            .findById(id)
            .map(c -> ResponseEntity.ok(MovieMapper.toMovieResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest request) {

        return movieService
            .update(id, MovieMapper.toMovie(request))
            .map(c -> ResponseEntity.ok(MovieMapper.toMovieResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category) {
        List<MovieResponse> list = movieService
            .findByCategory(category)
            .stream()
            .map(MovieMapper::toMovieResponse)
            .toList();

        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByMovieId(@PathVariable Long id) {
        Optional<Movie> optionalMovie = movieService.findById(id);

        if (optionalMovie.isPresent()) {
            movieService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
