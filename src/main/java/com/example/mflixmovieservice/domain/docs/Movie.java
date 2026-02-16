package com.example.mflixmovieservice.domain.docs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "movies")
@Getter
@Setter
//@CompoundIndex(
//        name = "idx_movies_fts",
//        def = "{'title': 'text', 'plot': 'text'}"
//)
public class Movie {
    /**
     * CompoundIndex(def = "{'title': 'text', 'plot': 'text'}")
     * We are creating a text based index in this collection.
     * Text based indexes allow FTS, Full Text Search on collections
     */

    @Id
    private String id;

    private String title;

    private String plot;

    @Field("fullplot")
    private String fullPlot;

    private String poster;

    private String type;

    private List<String> genres;

    private List<String> cast;

    private List<String> directors;

    private List<String> languages;

    private Integer runtime;

    private LocalDate released;

    private Imdb imdb;

    private Awards awards;

}
