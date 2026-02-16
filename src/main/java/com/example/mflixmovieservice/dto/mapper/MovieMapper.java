package com.example.mflixmovieservice.dto.mapper;

import com.example.mflixmovieservice.domain.docs.Movie;
import com.example.mflixmovieservice.dto.response.MovieItem;
import com.example.mflixmovieservice.dto.response.MovieListItem;

public class MovieMapper {

    public static MovieListItem toMovieListItem(Movie movie) {
        return new MovieListItem(
                movie.getId(), movie.getTitle(), movie.getGenres(),
                movie.getImdb() != null ? movie.getImdb().rating() : 0,
                movie.getRuntime() == null ? 0 : movie.getRuntime(),
                movie.getPoster(), movie.getScore()
        );
    }

    public static MovieItem toMovieItem(Movie movie) {
        return new MovieItem(
                movie.getId(), movie.getTitle(), movie.getPlot(),
                movie.getFullPlot(), movie.getGenres(), movie.getCast(),
                movie.getDirectors(), movie.getLanguages(),
                movie.getImdb() != null ? movie.getImdb().rating() : 0,
                movie.getRuntime(), movie.getReleased(), movie.getPoster()
        );
    }
}
