package com.xurxodev.moviesandroidkata.domain.model;

import java.io.Serializable;

public class Movie implements Serializable {
    private String image;
    private String title;
    private String description;

    public Movie(String image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public Movie() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
