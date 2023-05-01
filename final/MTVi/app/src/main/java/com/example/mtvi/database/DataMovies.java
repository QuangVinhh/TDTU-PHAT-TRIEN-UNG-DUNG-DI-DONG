package com.example.mtvi.database;

public class DataMovies {
    int movie_id;
    String movie_name;
    String movie_genres;
    String movie_description;
    int movie_image;
    String movie_linked;

    public DataMovies() {
    }

    public DataMovies(int movie_id, String movie_name, String movie_genres, String movie_description, int movie_image, String movie_linked) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_genres = movie_genres;
        this.movie_description = movie_description;
        this.movie_image = movie_image;
        this.movie_linked = movie_linked;
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

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_linked() {
        return movie_linked;
    }

    public void setMovie_linked(String movie_linked) {
        this.movie_linked = movie_linked;
    }
}
