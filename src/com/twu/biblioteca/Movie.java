package com.twu.biblioteca;

public class Movie extends BibliotecaItem {

    private String director;
    private int rating;

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirector() {
        return this.director;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return this.rating;
    }

    public void printInfos() {
        System.out.println(this.getTitle() + ", " +
                this.getPublicationYear() + ", " +
                this.getDirector() + ", " +
                this.getRating());
    }
}
