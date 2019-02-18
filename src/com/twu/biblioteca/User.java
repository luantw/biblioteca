package com.twu.biblioteca;

import java.util.List;

public class User {

    private String libraryNumber;
    private String password;
    private boolean isLibrarian;
    private List<Book> checkedBoooks;

    public void User() {
        this.isLibrarian = false;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public String getLibraryNumber() {
        return this.libraryNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setLibrarian(boolean isLibrarian) {
        this.isLibrarian = isLibrarian;
    }

    public boolean isLibrarian() {
        return this.isLibrarian;
    }

    public List<Book> checkedBooks() {
        return this.checkedBoooks;
    }

    public void checkOutBook(Book book) {
        this.checkedBoooks.add(book);
    }

}
