package org.imdb.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "imdb")
public class Movie {
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private int startYear;
    private int endYear;
    private int runtimeMinutes;

    private String[] genres;
    double avgRating;
    int numVotes;
    List<Akas> akas;
    List<Directors> directors;
    List<Starring> starrings;

    public Movie(String tconst, String titleType, String primaryTitle,
                 String originalTitle,
                 boolean isAdult,
                 int startYear, int endYear, int runtimeMinutes,
                 String[] genres,
                 double avgRating, int numVotes, List<Akas> akas,
                 List<Directors> directors, List<Starring> starrings) {
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
        this.avgRating = avgRating;
        this.numVotes = numVotes;
        this.akas = akas;
        this.directors = directors;
        this.starrings = starrings;
    }

    public Movie(){}

    public String getTconst() {
        return tconst;
    }

    public String getTitleType() {
        return titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public boolean isAdult() {
        return isAdult;
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

    public List<Directors> getDirectors() {
        return directors;
    }

    public List<Starring> getActors() {
        return starrings;
    }
}
