package com.example.mflixmovieservice.exceptions;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String id) {
        super("Movie not found with id: " + id);
    }
}
