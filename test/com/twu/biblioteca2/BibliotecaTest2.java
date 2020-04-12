package com.twu.biblioteca2;

import com.twu.biblioteca.Book;
import com.twu.biblioteca.BookStorage;
import com.twu.biblioteca.BorrowedBook;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class BibliotecaTest2
{
    private BibliotecaApp2 bibliotecaApp2;
    private BookStorage bookStorage;
    private MovieStorage movieStorage;
    private BorrowedBook borrowedBooks;
    private BorrowedMovie borrowedMovies;
    private Collection<Book> bookList;
    private Collection<Movie> movieList;
    private JTextArea jTextArea;

    @Before
    public void setUp()
    {
        bibliotecaApp2 = new BibliotecaApp2();

        bibliotecaApp2.setBookStorage(new BookStorage());
        bookStorage = bibliotecaApp2.getBookStorage();

        bibliotecaApp2.setMovieStorage(new MovieStorage());
        movieStorage = bibliotecaApp2.getMovieStorage();

        bibliotecaApp2.setBorrowedBooks(new BorrowedBook());
        borrowedBooks = bibliotecaApp2.getBorrowedBooks();

        bibliotecaApp2.setBooks(bookStorage.getBookList());
        bookList = bookStorage.getBookList();

        bibliotecaApp2.setMovies(movieStorage.getMovieList());
        movieList = movieStorage.getMovieList();

        bibliotecaApp2.setBorrowedMovies(new BorrowedMovie());
        borrowedMovies = bibliotecaApp2.getBorrowedMovies();

        jTextArea = mock(JTextArea.class);
    }

    @Test
    public void shouldShowWelcomeMessage()
    {
        bibliotecaApp2.showWelcome(jTextArea);

        verify(jTextArea).append("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore! \n");
        verify(jTextArea).append("What do you want? \n");
    }

    @Test
    public void shouldShowBookList()
    {
        bibliotecaApp2.viewBooks(bookList, jTextArea);

        for(Book book: bookList)
        {
            verify(jTextArea).append(book.getName() + " " + book.getAuthor() + " " + book.getPublishedYear() + "\n");
        }
    }

    @Test
    public void shouldShowMovieList()
    {
        bibliotecaApp2.viewMovies(movieList, jTextArea);

        for(Movie movie: movieList)
        {
            verify(jTextArea).append(movie.getName() + " " + movie.getYear() + " " + movie.getDirector() + " " + movie.getRating() + "\n");
        }
    }

    @Test
    public void shouldAddBorrowedBookListAfterCheckoutSuccessfully()
    {
        bibliotecaApp2.checkoutBook("book1");
        assertThat(borrowedBooks.getBorrowedBookByName("book1"), is(new Book("book1", "Patricia Aakhus", 1990)));
    }

    @Test
    public void shouldAddBorrowedMovieListAfterCheckoutSuccessfully()
    {
        bibliotecaApp2.checkoutMovie("movie1");
        assertThat(borrowedMovies.getBorrowedMovieByName("movie1"), is(new Movie("movie1", 2000, "Teagan Bowes", "10")));
    }

    @Test
    public void shouldShowDialogAndReturnBookNameAfterCheckoutABook()
    {
        Object[] selectionBooks = bookList.toArray();
        assertThat(bibliotecaApp2.showDialog("Check out a book", selectionBooks).toString(), is("book2"));
    }

    @Test
    public void shouldShowDialogAndReturnMovieNameAfterCheckoutAMovie()
    {
        Object[] selectionMovies = movieList.toArray();
        assertThat(bibliotecaApp2.showDialog("Check out a movie", selectionMovies).toString(), is("movie3"));
    }

    @Test
    public void shouldCallCheckoutBookMethodWhenCheckOutBook()
    {
        Object[] selectionBooks = bookList.toArray();
        BibliotecaApp2 spy = spy(bibliotecaApp2);
        spy.checkoutDialogHelper(selectionBooks, "book", jTextArea);
        verify(spy, times(1)).checkoutBook("book2");
    }

    @Test
    public void shouldCallCheckoutMovieMethodWhenCheckOutBook()
    {
        Object[] selectionMovies = movieList.toArray();
        BibliotecaApp2 spy = spy(bibliotecaApp2);
        spy.checkoutDialogHelper(selectionMovies, "movie", jTextArea);
        verify(spy, times(1)).checkoutMovie("movie3");
    }

    @Test
    public void shouldCallReturnBookMethodWhenReturnBook()
    {
        bibliotecaApp2.checkoutBook("book1");
        Object[] selectionBooks = borrowedBooks.getBorrowedBookList().toArray();

        BibliotecaApp2 spy = spy(bibliotecaApp2);
        spy.returnDialogHelper(selectionBooks, "book", jTextArea);
        verify(spy, times(1)).returnBook("book1");
    }
}
