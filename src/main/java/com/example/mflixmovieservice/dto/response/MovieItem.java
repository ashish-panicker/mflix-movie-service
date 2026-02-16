package com.example.mflixmovieservice.dto.response;

import java.time.LocalDate;
import java.util.List;

public record MovieItem (
        String id,
        String title,
        String plot,
        String fullPlot,
        List<String> genres,
        List<String> cast,
        List<String> directors,
        List<String> languages,
        double imdbRating,
        int runtime,
        LocalDate released,
        String poster
){
}
