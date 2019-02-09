package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {

        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great books titles in Bangalore!");
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void printMenu() {
        System.out.println("1 - List all books");
        System.out.println("0 - Quit Application");

        /*Scanner keyboard = new Scanner(System.in);
        int option = keyboard.nextInt();*/

    }

    public void selectOption(int option) {
        switch (option) {
            case 1:
                listAllBooks();
                break;
            case 0:
                System.out.println("Exiting application.");
                //System.exit(0);
                break;
            default:
                System.out.println("Please select a valid option!");
        }
    }

    public void listAllBooks() {
        for(int i=0; i<this.books.size(); i++) {
            boolean available = !this.books.get(i).getCheckOut();
            if (available) {
                System.out.println(this.books.get(i).getTitle());
            }
        }
    }

    public void checkout(Book book) {
        boolean isValid = false;
        for(int i=0; i<this.books.size(); i++) {
            if (book.getTitle() == this.books.get(i).getTitle()) {
                isValid = true;
                this.books.get(i).setCheckOut(true);
                break;
            }
        }

        if (!isValid) {
            System.out.println("This is not a valid book to return");
        }
    }

    /*public ArrayList<Book> getBooks() {
        return this.books;
    }*/

}
