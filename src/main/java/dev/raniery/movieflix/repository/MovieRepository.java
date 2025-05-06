package dev.raniery.movieflix.repository;

import dev.raniery.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
