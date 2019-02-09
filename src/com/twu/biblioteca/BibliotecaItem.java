package com.twu.biblioteca;

public class BibliotecaItem {
    private String title;
    private int publicationYear;
    private boolean unavailable;

    public void BibliotecaItem() {
        this.unavailable = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setPublicationYear(int year) {
        this.publicationYear = year;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public void setCheckOut(boolean unavailable) {
        if (unavailable && this.unavailable) {
            System.out.println("Sorry, that book is not available");
            return;
        }

        this.unavailable = unavailable;
        if (this.unavailable) {
            System.out.println("Thank you! Enjoy the book");
        }

        if (!this.unavailable) {
            System.out.println("Thank you for returning the book");
        }
    }

    public boolean getCheckOut() {
        return this.unavailable;
    }
}
