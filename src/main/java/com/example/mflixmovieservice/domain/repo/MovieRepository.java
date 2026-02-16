package com.example.mflixmovieservice.domain.repo;

import com.example.mflixmovieservice.domain.docs.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {

    Page<Movie> findByGenresContainingIgnoreCase(String genre, Pageable pageable);

    Page<Movie> findByImdbRatingNotNull(Pageable pageable);
}
