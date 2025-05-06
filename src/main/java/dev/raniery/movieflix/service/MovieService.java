package dev.raniery.movieflix.service;

import dev.raniery.movieflix.entity.Movie;
import dev.raniery.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public Movie save(Movie streaming) {
        return repository.save(streaming);
    }

    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
