package dev.raniery.movieflix.repository;

import dev.raniery.movieflix.entity.Category;
import dev.raniery.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategories(List<Category> categories);

    List<Movie> findTop5ByOrderByRatingDesc();
}
