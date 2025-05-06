package dev.raniery.movieflix.service;

import dev.raniery.movieflix.entity.Movie;
import dev.raniery.movieflix.entity.Category;
import dev.raniery.movieflix.entity.Streaming;
import dev.raniery.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return repository.save(movie);
    }

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();

        categories.forEach(c -> {
            categoryService.findById(c.getId()).ifPresent(categoriesFound::add);
        });

         return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();

        streamings.forEach(s -> {
            streamingService.findById(s.getId()).ifPresent(streamingsFound::add);
        });

        return streamingsFound;
    }
}
