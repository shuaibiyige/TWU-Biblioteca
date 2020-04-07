package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExampleTest
{
    private BibliotecaApp bibliotecaApp;
    private PrintStream printStream;
    private Collection<Book> actual;
    private BookStorage bookStorage;

    @Before
    public void init()
    {
        printStream = mock(PrintStream.class);
        bibliotecaApp = new BibliotecaApp();
        actual = new BookStorage().getBookList();
        bookStorage = new BookStorage();
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
        verify(printStream).println("B. Quit");
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
        bibliotecaApp.viewBooks(printStream);

        for(Book book: actual)
        {
            verify(printStream).println(book.toString());
        }
    }

    @Test
    public void shouldShowInvalidMessage()
    {
        bibliotecaApp.notifyInvalidMessage(printStream);
        verify(printStream).println("Please select a valid option!");
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

        int result = bibliotecaApp.checkout(bookStorage, actual, printStream, new Scanner(inputStream));
        assertThat(result, is(0));
    }
}
