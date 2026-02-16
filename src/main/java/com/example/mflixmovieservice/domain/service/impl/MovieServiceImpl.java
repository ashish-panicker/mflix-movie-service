package com.example.mflixmovieservice.domain.service.impl;

import com.example.mflixmovieservice.domain.repo.MovieRepository;
import com.example.mflixmovieservice.domain.service.MovieService;
import com.example.mflixmovieservice.dto.mapper.MovieMapper;
import com.example.mflixmovieservice.dto.response.MovieItem;
import com.example.mflixmovieservice.dto.response.MovieListItem;
import com.example.mflixmovieservice.exceptions.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Override
    public Page<MovieListItem> listMovies(Pageable pageable) {
        return repository.findAll(pageable)
                .map(MovieMapper::toMovieListItem);
    }

    @Override
    public Page<MovieListItem> listRatedMovies(Pageable pageable) {
        return repository.findByImdbRatingNotNull(pageable)
                .map(MovieMapper::toMovieListItem);
    }

    @Override
    public Page<MovieListItem> getByGenre(String genre, Pageable pageable) {
        return repository.findByGenresContainingIgnoreCase(genre, pageable)
                .map(MovieMapper::toMovieListItem);
    }

    @Override
    public MovieItem getMovieById(String id) {
        var movie = repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        return MovieMapper.toMovieItem(movie);
    }

    @Override
    public Page<MovieListItem> findByGenreAndMinRating(String genre, double rating, Pageable pageable) {
        return repository.findByGenreAndMinRating(genre, rating, pageable)
                .map(MovieMapper::toMovieListItem);
    }

    @Override
    public Page<MovieListItem> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return repository.findByReleaseDateBetween(startDate, endDate, pageable)
                .map(MovieMapper::toMovieListItem);
    }
}
