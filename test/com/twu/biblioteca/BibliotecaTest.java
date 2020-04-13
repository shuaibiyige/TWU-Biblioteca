package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BibliotecaTest
{
    private BibliotecaApp bibliotecaApp;
    private PrintStream printStream;
    private Collection<Book> actual;
    private BookStorage bookStorage;
    private BorrowedBook borrowedBooks;

    @Before
    public void init()
    {
        printStream = mock(PrintStream.class);
        bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.setBookStorage(new BookStorage());
        bookStorage = bibliotecaApp.getBookStorage();

        bibliotecaApp.setBorrowedBooks(new BorrowedBook());
        borrowedBooks = bibliotecaApp.getBorrowedBooks();

        bibliotecaApp.setBookList(bookStorage.getBookList());
        actual = bibliotecaApp.getBookList();
    }

    @Test
    public void shouldShowWelcomeMessage()
    {
        bibliotecaApp.showWelcome(printStream);

        verify(printStream).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @Test
    public void shouldShowMenu()
    {
        bibliotecaApp.showMenu(printStream);
        verify(printStream).println("A. List of books");
        verify(printStream).println("B. Check out a book");
        verify(printStream).println("C. Return a book");
        verify(printStream).println("D. Quit");
    }

    @Test
    public void shouldReturnInput()
    {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("a".getBytes());
        System.setIn(inputStream);

        String actual = bibliotecaApp.getUserInput(new Scanner(inputStream));
        assertThat(actual, is("a"));
    }

    @Test
    public void shouldShowBookList()
    {
        bibliotecaApp.viewBooks(actual, printStream);

        verify(printStream).printf("%-10s", "Name");
        verify(printStream).printf("|%-15s", "Author");
        verify(printStream).printf("|%-15s", "Author");

        for(Book book: actual)
        {
            verify(printStream).printf("%-10s", book.getName());
            verify(printStream).printf("|%-15s", book.getAuthor());
            verify(printStream).printf("|%-15s\n", book.getPublishedYear());
        }
    }

    @Test
    public void shouldShowInvalidMessage()
    {
        bibliotecaApp.notifyInvalidMessage(printStream);
        verify(printStream).println("Please select a valid option!");
    }

    @Test
    public void shouldReturnTrueWhenBookNameIsNotValid()
    {
        assertThat(bibliotecaApp.isBookNameValid(actual,"book1"), is(true));
    }

    @Test
    public void shouldReturnFalseWhenBookNameIsNotValid()
    {
        assertThat(bibliotecaApp.isBookNameValid(actual,"aa"), is(false));
    }

    @Test
    public void shouldReturn0WhenCheckoutAValidBook()
    {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("book1".getBytes());
        System.setIn(inputStream);

        int result = bibliotecaApp.checkout(bookStorage, borrowedBooks, actual, printStream, new Scanner(inputStream));
        assertThat(result, is(0));
    }

    @Test
    public void shouldAddBorrowedBookListAfterCheckoutSuccessfully()
    {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("book1".getBytes());
        System.setIn(inputStream);

        bibliotecaApp.checkout(bookStorage, borrowedBooks, actual, printStream, new Scanner(inputStream));
        assertThat(borrowedBooks.getBorrowedBookByName("book1"), is(new Book("book1", "Patricia Aakhus", 1990)));
    }

    @Test
    public void shouldReturn0WhenReturnBookSuccessfully()
    {
        borrowedBooks.addBorrowedBook(bookStorage.getBookByName("book1"));
        bookStorage.removeBook("book1");

        ByteArrayInputStream inputStream = new ByteArrayInputStream("book1".getBytes());
        System.setIn(inputStream);

        int result = bibliotecaApp.returnBook(bookStorage, borrowedBooks, printStream, new Scanner(inputStream));
        assertThat(result, is(0));
    }
}
