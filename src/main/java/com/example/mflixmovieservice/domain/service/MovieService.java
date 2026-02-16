package com.example.mflixmovieservice.domain.service;

import com.example.mflixmovieservice.domain.docs.Movie;
import com.example.mflixmovieservice.dto.response.MovieItem;
import com.example.mflixmovieservice.dto.response.MovieListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface MovieService {


    Page<MovieListItem> listMovies(Pageable pageable);

    Page<MovieListItem> listRatedMovies(Pageable pageable);

    Page<MovieListItem> getByGenre(String genre, Pageable pageable);

    MovieItem getMovieById(String id);

    Page<MovieListItem> findByGenreAndMinRating(String genre, double rating, Pageable pageable);

    Page<MovieListItem> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<MovieListItem> keyWordSearch(String keyWord, Pageable pageable);

    Page<MovieListItem> keyWordSearchByMongoTemplate(String keyWord, Pageable pageable);
}
