package com.twu.biblioteca;

import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookstorageTest
{
    @Test
    public void shouldContainAListOfBooks()
    {
        Collection<Book> actual = new BookStorage().getBookList();

        assertThat(actual, hasItems(
                new Book("book1", "Patricia Aakhus", 1990),
                new Book("book2", "Lynn Abbey", 1945),
                new Book("book3", "Megan Abbott", 1996)));
    }

    @Test
    public void shouldGetBookByName()
    {
        Book actual = new BookStorage().getBookByName("book1");
        assertThat(actual, is(new Book("book1", "Patricia Aakhus", 1990)));
    }

    @Test
    public void shouldRemoveBookByName()
    {
        BookStorage bookStorage = new BookStorage();
        Collection<Book> actual = bookStorage.getBookList();
        bookStorage.removeBook("book1");
        assertThat(actual, not(hasItems(new Book("book1", "Patricia Aakhus", 1990))));
    }
}
