package com.xurxodev.moviesandroidkata.domain.entity;

public class MovieEntity {

    private String image;
    private String title;
    private String description;

    public MovieEntity(String image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public String getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
