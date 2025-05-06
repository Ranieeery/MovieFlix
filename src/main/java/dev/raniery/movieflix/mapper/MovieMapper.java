package dev.raniery.movieflix.mapper;

import dev.raniery.movieflix.controller.request.MovieRequest;
import dev.raniery.movieflix.controller.response.CategoryResponse;
import dev.raniery.movieflix.controller.response.MovieResponse;
import dev.raniery.movieflix.controller.response.StreamingResponse;
import dev.raniery.movieflix.entity.Category;
import dev.raniery.movieflix.entity.Movie;
import dev.raniery.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {
    public Movie toMovie(MovieRequest movieRequest) {
        List<Category> categories = movieRequest
            .categories()
            .stream()
            .map(c_id -> Category.builder().id(c_id).build())
            .toList();

        List<Streaming> streamings = movieRequest
            .streamings()
            .stream()
            .map(c_id -> Streaming.builder().id(c_id).build())
            .toList();

        return Movie
            .builder()
            .title(movieRequest.title())
            .description(movieRequest.description())
            .releaseDate(movieRequest.releaseDate())
            .rating(movieRequest.rating())
            .categories(categories)
            .streamings(streamings)
            .build();
    }

    public MovieResponse toMovieResponse(Movie movie) {
        List<CategoryResponse> categories = movie
            .getCategories()
            .stream()
            .map(CategoryMapper::toCategoryResponse)
            .toList();

        List<StreamingResponse> streamings = movie
            .getStreamings()
            .stream()
            .map(StreamingMapper::toStreamingResponse)
            .toList();

        return MovieResponse
            .builder()
            .id(movie.getId())
            .title(movie.getTitle())
            .description(movie.getDescription())
            .releaseDate(movie.getReleaseDate())
            .rating(movie.getRating())
            .categories(categories)
            .streamings(streamings)
            .build();
    }
}
