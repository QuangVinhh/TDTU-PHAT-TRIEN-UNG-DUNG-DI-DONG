package com.example.demo.database;

public class DataMovies {

    String movie_name;
    String movie_genres;
    String movie_description;
    int movie_image;

    public DataMovies() {

    }

    public DataMovies(String movie_name, String movie_genres, String movie_description, int movie_image) {
        this.movie_name = movie_name;
        this.movie_genres = movie_genres;
        this.movie_description = movie_description;
        this.movie_image = movie_image;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_genres() {
        return movie_genres;
    }

    public void setMovie_genres(String movie_genres) {
        this.movie_genres = movie_genres;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public void setMovie_description(String movie_description) {
        this.movie_description = movie_description;
    }

    public int getMovie_image() {
        return movie_image;
    }

    public void setMovie_image(int movie_image) {
        this.movie_image = movie_image;
    }
}
