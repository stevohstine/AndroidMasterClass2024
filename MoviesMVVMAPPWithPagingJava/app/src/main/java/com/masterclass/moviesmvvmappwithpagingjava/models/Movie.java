package com.masterclass.moviesmvvmappwithpagingjava.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer page) {
        this.id = page;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
       return false;
    }
}
