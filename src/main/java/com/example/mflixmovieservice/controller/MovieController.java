package com.example.mflixmovieservice.controller;

import com.example.mflixmovieservice.domain.service.MovieService;
import com.example.mflixmovieservice.dto.response.MovieItem;
import com.example.mflixmovieservice.dto.response.MovieListItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

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

}
