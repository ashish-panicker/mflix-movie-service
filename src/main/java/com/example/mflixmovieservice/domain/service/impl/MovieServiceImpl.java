package com.example.mflixmovieservice.domain.service.impl;

import com.example.mflixmovieservice.domain.docs.Movie;
import com.example.mflixmovieservice.domain.repo.MovieRepository;
import com.example.mflixmovieservice.domain.service.MovieService;
import com.example.mflixmovieservice.dto.mapper.MovieMapper;
import com.example.mflixmovieservice.dto.response.MovieItem;
import com.example.mflixmovieservice.dto.response.MovieListItem;
import com.example.mflixmovieservice.exceptions.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;
    private final MongoTemplate mongoTemplate;

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

    @Override
    public Page<MovieListItem> keyWordSearch(String keyWord, Pageable pageable) {
        var criteria = TextCriteria.forDefaultLanguage().matching(keyWord);
        Pageable sortedPage = PageRequest.of(
                pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Order.desc("score"))
        );
        return repository.findAllBy(criteria, sortedPage)
                .map(MovieMapper::toMovieListItem);
    }

    @Override
    public Page<MovieListItem> keyWordSearchByMongoTemplate(String keyWord, Pageable pageable) {
        // Define the text criteria
        var criteria = TextCriteria.forDefaultLanguage().matching(keyWord);

        // var filterCriteria = Criteria.where("deleted").is(false);

        // Build the query
        var query = TextQuery.queryText(criteria).sortByScore().with(pageable);

        // Execute the query
        var movies = mongoTemplate.find(query, Movie.class);

        // Count the total result
        long total = mongoTemplate.count(Query.query(criteria), Movie.class);

        // map to DTOs
        var dtoList = movies.stream().map(MovieMapper::toMovieListItem).toList();

        return new PageImpl<>(dtoList, pageable, total);
    }
}
