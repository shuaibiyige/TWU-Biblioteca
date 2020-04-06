package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookstorageTest
{
    @Test
    public void shouldContainAListOfBooks()
    {
        List<Book> actual = BookStorage.getBookList();

        assertThat(actual, hasItems(
                new Book("Patricia Aakhus", 1990),
                new Book("Lynn Abbey", 1945),
                new Book("Megan Abbott", 1996)));
    }
}
