package com.example.mflixmovieservice.controller;

import com.example.mflixmovieservice.domain.service.MovieService;
import com.example.mflixmovieservice.dto.response.MovieItem;
import com.example.mflixmovieservice.dto.response.MovieListItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    /**
     * Using pageable urls
     * GET /api/v1/movies?page=0&size=10
     * GET /api/v1/movies?page=0&size=10&sort=title,asc
     * GET /api/v1/movies?page=0&size=10&sort=imdb.rating,desc
     * GET /api/v1/movies?page=0&size=10&sort=runtime,desc&sort=title,asc
     */

    @GetMapping
    public Page<MovieListItem> listMovies(Pageable pageable) {
        return service.listRatedMovies(pageable);
    }

    @GetMapping("genre/{genre}")
    public Page<MovieListItem> getByGenre(@PathVariable String genre, Pageable pageable) {
        return service.getByGenre(genre, pageable);
    }

    @GetMapping("/{id}")
    public MovieItem getMovieById(@PathVariable String id) {
        return service.getMovieById(id);
    }

    @GetMapping("/rated")
    public Page<MovieListItem> listRatedMovies(Pageable pageable) {
        return service.listRatedMovies(pageable);
    }

    @GetMapping("/filter/genre")
    public Page<MovieListItem> getByGenreAndMinRating(@RequestParam String genre,
                                                      @RequestParam double rating,
                                                      Pageable pageable) {
        return service.findByGenreAndMinRating(genre, rating, pageable);
    }

    @GetMapping("/filter/dates")
    public Page<MovieListItem> getByReleaseDateBetween(@RequestParam LocalDate start,
                                                       @RequestParam LocalDate end,
                                                       Pageable pageable) {
        return service.findByReleaseDateBetween(start, end, pageable);
    }

    @GetMapping("/search")
    public Page<MovieListItem> searchByKeyWord(@RequestParam String keyWord, Pageable pageable) {
        return service.keyWordSearch(keyWord, pageable);
    }

}
