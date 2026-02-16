package com.example.mflixmovieservice.domain.service;

import com.example.mflixmovieservice.dto.response.MovieItem;
import com.example.mflixmovieservice.dto.response.MovieListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Page<MovieListItem> listMovies(Pageable pageable);

    Page<MovieListItem> listRatedMovies(Pageable pageable);

    Page<MovieListItem> getByGenre(String genre, Pageable pageable);

    MovieItem getMovieById(String id);
}
