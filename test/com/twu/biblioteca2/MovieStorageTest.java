package com.twu.biblioteca2;

import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class MovieStorageTest
{
    @Test
    public void shouldContainAListOfMovies()
    {
        Collection<Movie> actual = new MovieStorage().getMovieList();

        assertThat(actual, hasItems(
                new Movie("movie1", 2000, "Teagan Bowes", "10"),
                new Movie("movie2", 2004, "Stacey Lopez", "4"),
                new Movie("movie3", 2018, "Kayla Finch", "2")));
    }

    @Test
    public void shouldGetMovieByName()
    {
        Movie actual = new MovieStorage().getMovieByName("movie1");
        assertThat(actual, is(new Movie("movie1", 2000, "Teagan Bowes", "10")));
    }

    @Test
    public void shouldRemoveMovieByName()
    {
        MovieStorage movieStorage = new MovieStorage();
        Collection<Movie> actual = movieStorage.getMovieList();
        movieStorage.removeMovie("movie1");
        assertThat(actual, not(hasItems(new Movie("movie1", 2000, "Teagan Bowes", "10"))));
    }
}
