package com.example.mtvi_ver2.database.data;

public class DataMovies {
    int movie_id;
    String movie_image;
    String movie_name;
    String movie_genres;
    String movie_detail;
    String movie_link;

    public DataMovies(){

    }

    @Override
    public String toString() {
        return "DataMovies{" +
                "movie_id=" + movie_id +
                ", movie_image='" + movie_image + '\'' +
                ", movie_name='" + movie_name + '\'' +
                ", movie_genres='" + movie_genres + '\'' +
                ", movie_detail='" + movie_detail + '\'' +
                ", movie_link='" + movie_link + '\'' +
                '}';
    }

    public DataMovies(int movie_id, String movie_image, String movie_name, String movie_genres, String movie_detail, String movie_link) {
        this.movie_id = movie_id;
        this.movie_image = movie_image;
        this.movie_name = movie_name;
        this.movie_genres = movie_genres;
        this.movie_detail = movie_detail;
        this.movie_link = movie_link;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_image() {
        return movie_image;
    }

    public void setMovie_image(String movie_image) {
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

    public String getMovie_detail() {
        return movie_detail;
    }

    public void setMovie_detail(String movie_detail) {
        this.movie_detail = movie_detail;
    }

    public String getMovie_link() {
        return movie_link;
    }

    public void setMovie_link(String movie_link) {
        this.movie_link = movie_link;
    }
}
