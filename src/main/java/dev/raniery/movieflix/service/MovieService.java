package dev.raniery.movieflix.service;

import dev.raniery.movieflix.entity.Category;
import dev.raniery.movieflix.entity.Movie;
import dev.raniery.movieflix.entity.Streaming;
import dev.raniery.movieflix.repository.MovieRepository;
import dev.raniery.movieflix.exception.ResourceNotFoundException;
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
    private final MovieRepository movieRepository;

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

    public List<Movie> findByCategory(Long categoryId) {
        return repository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public Optional<Movie> update(Long id, Movie movie) {
        Optional<Movie> optional = movieRepository.findById(id);

//        optional.ifPresent(c -> {
//            List<Category> categories = this.findCategories(movie.getCategories());
//            List<Streaming> streamings = this.findStreamings(movie.getStreamings());
//
//            c.setTitle(movie.getTitle());
//            c.setDescription(movie.getDescription());
//            c.setReleaseDate(movie.getReleaseDate());
//            c.setRating(movie.getRating());
//
//            c.getCategories().clear();
//            c.setCategories(categories);
//            c.getStreamings().clear();
//            c.setStreamings(streamings);
//
//            repository.save(c);
//        });

        optional.map(m -> {
            List<Category> categories = this.findCategories(movie.getCategories());
            List<Streaming> streamings = this.findStreamings(movie.getStreamings());

            if (movie.getTitle() != null) {
                m.setTitle(movie.getTitle());
            }

            if (movie.getDescription() != null) {
                m.setDescription(movie.getDescription());
            }
            
            if (movie.getReleaseDate() != null) {
                m.setReleaseDate(movie.getReleaseDate());
            }
            
            if (movie.getRating() != null) {
                m.setRating(movie.getRating());
            }
            
            if (!categories.isEmpty()) {
                m.getCategories().clear();
                m.setCategories(categories);
            }
            
            if (!streamings.isEmpty()) {
                m.getStreamings().clear();
                m.setStreamings(streamings);
            }
            
            return repository.save(m);
        });

        return optional;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {

        List<Category> categoriesFound = new ArrayList<>();
        List<Long> notFoundIds = new ArrayList<>();

        categories.forEach(c -> {
            Optional<Category> category = categoryService.findById(c.getId());

            if (category.isPresent()) {
                categoriesFound.add(category.get());
            } else {
                notFoundIds.add(c.getId());
            }
        });

        if (!notFoundIds.isEmpty()) {
            throw ResourceNotFoundException.forCategoryId(notFoundIds.getFirst());
        }

        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        
        List<Streaming> streamingsFound = new ArrayList<>();
        List<Long> notFoundIds = new ArrayList<>();

        streamings.forEach(s -> {
            Optional<Streaming> streaming = streamingService.findById(s.getId());

            if (streaming.isPresent()) {
                streamingsFound.add(streaming.get());
            } else {
                notFoundIds.add(s.getId());
            }
        });

        if (!notFoundIds.isEmpty()) {
            throw ResourceNotFoundException.forStreamingId(notFoundIds.getFirst());
        }

        return streamingsFound;
    }
}
