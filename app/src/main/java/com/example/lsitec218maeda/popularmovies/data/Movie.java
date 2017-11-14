package com.example.lsitec218maeda.popularmovies.data;

import java.io.Serializable;

/**
 * Created by lsitec218.maeda on 09/11/17.
 * Movie Details provided by movies list
 */

public class Movie implements Serializable {
    private int id;
    private int voteCount;
    private boolean hasVideo;
    private double voteAverage;
    private String title;
    private double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private String backdropPath;
    private boolean isAdultOnly;
    private String overview;
    private String releaseDate;

    public Movie(int id, int voteCount, boolean hasVideo, double voteAverage, String title, double popularity, String posterPath, String originalLanguage, String originalTitle, String backdropPath, boolean isAdultOnly, String overview, String releaseDate) {
        this.id = id;
        this.voteCount = voteCount;
        this.hasVideo = hasVideo;
        this.voteAverage= voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.isAdultOnly = isAdultOnly;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public String getTitle(){
        return title;
    }

    public String getOriginalTitle() { return originalTitle; }

    public String getPosterPath(){
        return posterPath;
    }

    public double getVoteAverage() { return voteAverage; }

    public String getOverview(){ return overview; }

    public String getReleaseDate(){ return releaseDate; }

    public int getId() {
        return id;
    }
}
