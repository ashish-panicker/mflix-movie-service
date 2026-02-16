package com.example.mflixmovieservice.domain.repo;

import com.example.mflixmovieservice.domain.docs.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;

public interface MovieRepository extends MongoRepository<Movie, String> {

    Page<Movie> findByGenresContainingIgnoreCase(String genre, Pageable pageable);

    Page<Movie> findByImdbRatingNotNull(Pageable pageable);

    /**
     * // {$options: 'i'}: case insensitive search
     * db.movies.find({
     * genres: {$regex: "Action", $options: 'i'},
     * "imdb.rating": {$gte: 8.0}
     * })
     */
    @Query(value = """
                    {
                        'genres': {$regex:  ?0, $options:  'i'},
                        'imdb.rating': {$gte: ?1}
                    }
            """)
    Page<Movie> findByGenreAndMinRating(String genre, double rating, Pageable pageable);

    @Query(value = """
                    {
                        'released': {$gte:  ?0, $lte:  ?1},
                        'imdb.rating': {$nte:  null}
                    }
            """)
    Page<Movie> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    // Full text search
    // {$text: {$search: 'keyword'}}
    Page<Movie> findAllBy(TextCriteria criteria, Pageable pageable);
}
