package com.twu.biblioteca;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class ExampleTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void setBooks() {
        for (int i=1; i<=7; i++) {
            Book book = new Book();
            book.setTitle("Game of thrones vol " + i);
            book.setPublicationYear(1995 + i);
            book.setAuthor("George R R Martin");
            books.add(book);
        }
    }

    @Before
    public void setMovies() {
        for (int i=0; i<3; i++) {
            Movie movie = new Movie();
            movie.setTitle("The Hobbit vol " + (i + 1));
            movie.setPublicationYear(2012 + i);
            movie.setDirector("Peter Jackson");
            movie.setRating(8 - i);
            movies.add(movie);
        }
    }

    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void testWelcomeMessage() {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.main(null);
        String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great books titles in Bangalore!\n";
        assertThat(outContent.toString(), is(equalTo(welcomeMessage)));
    }

    @Test
    public void testBook() {
        Book book = new Book();
        book.setTitle("Game of Thrones");
        String title = "Game of Thrones";

        assertThat(title, is(equalTo(book.getTitle())));
    }

    @Test
    public void sizeOfBooksList() {
        assertThat(7, is(equalTo(books.size())));
    }

    @Test
    public void listAllBooks() {
        for(int i=0; i<7; i++) {
            assertThat("Game of thrones vol " + (i + 1), is(equalTo(books.get(i).getTitle())));
        }
    }

    @Test
    public void testPublicationYearOfABook() {
        Book book = new Book();
        book.setTitle("Game of thrones vol 1");
        book.setPublicationYear(1996);

        assertThat(1996, is(equalTo(book.getPublicationYear())));
    }

    @Test
    public void getAuthorOfABook() {
        Book book = new Book();
        book.setAuthor("George R R Martin");

        assertThat("George R R Martin", is(equalTo(book.getAuthor())));
    }

    @Test
    public void listPublicationYearsAndAuthorsFromBooks() {
        for(int i=0; i<7; i++) {
            assertThat(1996 + i, is(equalTo(books.get(i).getPublicationYear())));
            assertThat("George R R Martin", is(equalTo(books.get(i).getAuthor())));
        }
    }

    @Test
    public void showMenuList() {
        BibliotecaApp biblioteca = new BibliotecaApp();
        String menuList = "1 - List all books\n0 - Quit Application\n";
        biblioteca.printMenu();
        assertThat(outContent.toString(), is(equalTo(menuList)));
    }

    @Test
    public void testSelectListAllBooksOption() {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooks(books);
        biblioteca.selectOption(1);
        String listBooks = "";
        for(int i=0;i<books.size();i++) {
            listBooks += books.get(i).getTitle() + "\n";
        }

        assertThat(outContent.toString(), is(equalTo(listBooks)));

    }

    @Test
    public void selectAValidOption() {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooks(books);
        biblioteca.selectOption(888);

        String message = "Please select a valid option!\n";

        assertThat(outContent.toString(), is(equalTo(message)));
    }

    @Test
    public void quitApplication() {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooks(books);
        biblioteca.selectOption(0);

        String message = "Exiting application.\n";

        assertThat(outContent.toString(), is(equalTo(message)));
    }

    @Test
    public void checkOutABook() {
        Book book = new Book();
        book.setCheckOut(true);

        assertThat(book.getCheckOut(), is(true));
    }

    @Test
    public void testIfCheckedOutBooksDoesNotAppearInBooksList() {
        books.get(1).setCheckOut(true);
        String response = "Thank you! Enjoy the book\n";
        for(int i=0;i<books.size(); i++) {
            response += (i != 1)? books.get(i).getTitle() + "\n" : "";
        }

        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooks(books);
        biblioteca.listAllBooks();

        assertThat(outContent.toString(), is(equalTo(response)));
    }

    @Test
    public void checkOutSuccessMessage() {
        Book book = new Book();
        book.setCheckOut(true);
        String message = "Thank you! Enjoy the book\n";

        assertThat(outContent.toString(), is(equalTo(message)));
    }

    @Test
    public void checkOutFailureMessage() {
        Book book = new Book();
        book.setCheckOut(true);

        book.setCheckOut(true);
        String message = "Thank you! Enjoy the book\nSorry, that book is not available\n";

        assertThat(outContent.toString(), is(equalTo(message)));
    }

    @Test
    public void returnBook() {
        Book book = new Book();
        book.setCheckOut(true);
        book.setCheckOut(false);

        assertThat(book.getCheckOut(), is(false));
    }

    @Test
    public void returnedBookIsInTheAvailableList() {
        books.get(1).setCheckOut(true); // book unavailable
        books.get(1).setCheckOut(false); // book available again

        String response = "Thank you! Enjoy the book\nThank you for returning the book\n";
        for(int i=0;i<books.size(); i++) {
            response += books.get(i).getTitle() + "\n";
        }

        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooks(books);
        biblioteca.listAllBooks();

        assertThat(outContent.toString(), is(equalTo(response)));
    }

    @Test
    public void notifySuccessfulReturn() {
        Book book = new Book();
        book.setCheckOut(true);
        book.setCheckOut(false);
        String message = "Thank you! Enjoy the book\nThank you for returning the book\n";

        assertThat(outContent.toString(), is(equalTo(message)));
    }

    @Test
    public void notifyOnUnsuccessfulReturn() {
        Book book = new Book();
        book.setTitle("Lord of the rings");
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.setBooks(this.books);

        biblioteca.checkout(book);

        String message = "This is not a valid book to return\n";

        assertThat(outContent.toString(), is(equalTo(message)));
    }

    @Test
    public void testMovie() {
        Movie movie = new Movie();
        movie.setTitle("The Hobbit");
        movie.setPublicationYear(2012);
        movie.setDirector("Peter Jackson");
        movie.setRating(8);

        assertThat("The Hobbit", is(equalTo(movie.getTitle())));
        assertThat(2012, is(equalTo(movie.getPublicationYear())));
        assertThat("Peter Jackson", is(equalTo(movie.getDirector())));
        assertThat(8,is(equalTo(movie.getRating())));
    }

    @Test
    public void testListMovies() {
        String moviesInfo = "";
        for (int i=0; i<this.movies.size(); i++) {
            Movie movie = this.movies.get(i);
            moviesInfo += "The Hobbit vol " + (i + 1) + ", " +
                    (2012 + i) + ", " +
                    "Peter Jackson, " +
                    (8 - i) + "\n";
            movie.printInfos();
        }

        assertThat(outContent.toString(), is(equalTo(moviesInfo)));
    }
}
