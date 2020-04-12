package com.twu.biblioteca2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MovieStorage
{
    private static Map<String, Movie> movieList;

    static
    {
        movieList = new HashMap<String, Movie>();

        Movie movie1 = new Movie("movie1", 2000, "Teagan Bowes", "10");
        Movie movie2 = new Movie("movie2", 2004, "Stacey Lopez", "4");
        Movie movie3 = new Movie("movie3", 2018, "Kayla Finch", "2");

        movieList.put("movie1", movie1);
        movieList.put("movie2", movie2);
        movieList.put("movie3", movie3);
    }

    public Collection<Movie> getMovieList()
    {
        return movieList.values();
    }

    public void removeMovie(String name)
    {
        movieList.remove(name);
    }

    public void addMovie(Movie movie)
    {
        movieList.put(movie.getName(), movie);
    }

    public Movie getMovieByName(String name)
    {
        return movieList.get(name);
    }
}
