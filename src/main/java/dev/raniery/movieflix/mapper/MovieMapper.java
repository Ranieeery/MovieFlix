package dev.raniery.movieflix.mapper;

import dev.raniery.movieflix.controller.request.MovieRequest;
import dev.raniery.movieflix.controller.response.MovieResponse;
import dev.raniery.movieflix.entity.Movie;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieMapper {
    public Movie toMovie(MovieRequest movieRequest) {
        return Movie
            .builder()
            .title(movieRequest.title())
            .build();
    }

    public MovieResponse toMovieResponse(Movie movie) {
        return MovieResponse
            .builder()
            .id(movie.getId())
            .title(movie.getTitle())
            .build();
    }
}
