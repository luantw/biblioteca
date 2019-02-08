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

        /*Scanner keyboard = new Scanner(System.in);
        int option = keyboard.nextInt();*/

    }

    public void selectOption(int option) {
        switch (option) {
            case 1:
                listAllBooks();
                break;
        }
    }

    public void listAllBooks() {
        for(int i=0; i<this.books.size(); i++) {
            System.out.println(this.books.get(i).getTitle());
        }
    }

}
