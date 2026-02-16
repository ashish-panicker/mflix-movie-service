package com.example.mflixmovieservice.dto.response;

import java.util.List;

public record MovieListItem(
        String id,
        String title,
        List<String> genres,
        double imdbRating,
        int runtime,
        String poster
) {
}
