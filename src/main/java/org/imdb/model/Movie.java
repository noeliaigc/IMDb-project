package org.imdb.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "movies")
public class Movie {
    private String titleId;
    private String primaryTitle;
    private String originalTitle;
    private int startYear;
    private int endYear;
    private int runtimeMinutes;

    private String[] genres;
    double avgRating;
    int numVotes;
    List<Akas> akas;
    List<Director> directors;
    List<Actor> actors;

    public Movie(String titleId, String primaryTitle, String originalTitle,
                 int startYear, int endYear, int runtimeMinutes,
                 String[] genres,
                 double avgRating, int numVotes, List<Akas> akas,
                 List<Director> directors, List<Actor> actors) {
        this.titleId = titleId;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
        this.avgRating = avgRating;
        this.numVotes = numVotes;
        this.akas = akas;
        this.directors = directors;
        this.actors = actors;
    }

    public Movie(){}

    public String getTitleId() {
        return titleId;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }


    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }
    public String[] getGenres() {
        return genres;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public List<Akas> getAkas() {
        return akas;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public List<Actor> getActors() {
        return actors;
    }
}
