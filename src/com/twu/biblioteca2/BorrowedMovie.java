package com.twu.biblioteca2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BorrowedMovie
{
    private Map<String, Movie> borrowedMovieList;

    public BorrowedMovie()
    {
        borrowedMovieList = new HashMap<String, Movie>();
    }

    public Collection<Movie> getBorrowedMovieList()
    {
        return borrowedMovieList.values();
    }

    public void removeBorrowedMovie(String name)
    {
        borrowedMovieList.remove(name);
    }

    public void addBorrowedMovie(Movie movie)
    {
        borrowedMovieList.put(movie.getName(), movie);
    }

    public Movie getBorrowedMovieByName(String name)
    {
        return borrowedMovieList.get(name);
    }
}
