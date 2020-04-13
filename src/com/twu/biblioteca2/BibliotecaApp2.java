package com.twu.biblioteca2;

import com.twu.biblioteca.Book;
import com.twu.biblioteca.BookStorage;
import com.twu.biblioteca.BorrowedBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Map;

public class BibliotecaApp2 implements ActionListener
{
    private JFrame jFrame;
    private JTextArea textArea;
    private JButton bookList, movieList, checkoutBook, returnBook, aboutMe, checkoutMovie;
    private Collection<Book> books;
    private Collection<Movie> movies;

    private BookStorage bookStorage;
    private MovieStorage movieStorage;
    private BorrowedBook borrowedBooks;
    private BorrowedMovie borrowedMovies;

    private Account user;

    public static void main(String[] args)
    {
        BibliotecaApp2 bibliotecaApp2 = new BibliotecaApp2();

        bibliotecaApp2.appStart();
    }

    public void appStart()
    {
        initUI();
        bookStorage = new BookStorage();
        movieStorage =  new MovieStorage();
        books = bookStorage.getBookList();
        movies = movieStorage.getMovieList();
        borrowedBooks = new BorrowedBook();
        borrowedMovies = new BorrowedMovie();

        boolean isValidUser = login();
        while (!isValidUser)
            isValidUser = login();
        showWelcome(textArea);
    }

    public void initUI()
    {
        jFrame = new JFrame();
        JPanel jPanelTop = new JPanel();
        JPanel jPanelBottom = new JPanel();
        GridLayout layoutBottom = new GridLayout(3, 2);

        textArea = new JTextArea(20, 50);
        textArea.setLineWrap(true);

        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        bookList = new JButton("List of books");
        movieList = new JButton("List of movies");
        checkoutBook = new JButton("Check out a book");
        checkoutMovie = new JButton("Check out a movie");
        returnBook = new JButton("Return a book");
        aboutMe = new JButton("About me");

        bookList.addActionListener(this);
        movieList.addActionListener(this);
        checkoutBook.addActionListener(this);
        checkoutMovie.addActionListener(this);
        returnBook.addActionListener(this);
        aboutMe.addActionListener(this);

        jPanelTop.add(jScrollPane);
        jPanelBottom.add(bookList);
        jPanelBottom.add(movieList);
        jPanelBottom.add(checkoutBook);
        jPanelBottom.add(checkoutMovie);
        jPanelBottom.add(returnBook);
        jPanelBottom.add(aboutMe);

        jPanelBottom.setLayout(layoutBottom);

        jFrame.getContentPane().add(BorderLayout.CENTER, jPanelTop);
        jFrame.getContentPane().add(BorderLayout.SOUTH, jPanelBottom);
        jFrame.setSize(650, 500);
        jFrame.setVisible(true);
    }

    public boolean login()
    {
        Map<String, String> account = CustomizedDialog.showCustomDialog(jFrame, jFrame);
        String number = account.get("libraryNumber");
        String password = account.get("password");
        return isValidAccount(number, password);
    }

    public boolean isValidAccount(String number, String password)
    {
        boolean result = false;
        AccountStorage accountStorage = new AccountStorage();
        Collection<Account> accountList = accountStorage.getAccountList();
        for (Account account: accountList)
        {
            if (account.getLibraryNumber().equals(number) && account.getPassword().equals(password))
            {
                user = accountStorage.getAccountByName(number);
                result = true;
            }
        }
        return result;
    }

    public void showWelcome(JTextArea jTextArea)
    {
        jTextArea.append("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore! \n");
        jTextArea.append("What do you want? \n");
    }

    public void viewBooks(Collection<Book> bookList, JTextArea jTextArea)
    {
        jTextArea.append("\n");
        for(Book book: bookList) {
            jTextArea.append(book.getName() + " " + book.getAuthor() + " " + book.getPublishedYear() + "\n");
        }
    }

    public void viewMovies(Collection<Movie> movieList, JTextArea jTextArea)
    {
        jTextArea.append("\n");
        for(Movie movie: movieList) {
            jTextArea.append(movie.getName() + " " + movie.getYear() + " " + movie.getDirector() + " " + movie.getRating() + "\n");
        }
    }

    public void checkoutBook(String name)
    {
        borrowedBooks.addBorrowedBook(bookStorage.getBookByName(name));      // add to borrowed book list
        bookStorage.removeBook(name);
    }

    public void checkoutMovie(String name)
    {
        borrowedMovies.addBorrowedMovie(movieStorage.getMovieByName(name));      // add to borrowed movie list
        movieStorage.removeMovie(name);
    }

    public Object showDialog(String title, Object[] list)
    {
        return JOptionPane.showInputDialog(jFrame, "select one: ", title, JOptionPane.PLAIN_MESSAGE, null, list, list[0]);
    }

    // use the same method for different item
    public void checkoutDialogHelper(Object[] selection, String item, JTextArea textArea)
    {
        if (selection.length == 0)
            textArea.append("No " + item + " in the library! \n");
        else
        {
            if("book".equals(item))
            {
                Object o = showDialog("Check out a book", selection);
                if (o != null) {
                    checkoutBook(o.toString());
                    textArea.append("Check out Successfully \n");
                }
            }
            else
            {
                Object o = showDialog("Check out a movie", selection);
                if (o != null) {
                    checkoutMovie(o.toString());
                    textArea.append("Check out Successfully \n");
                }
            }
        }
    }

    public void returnDialogHelper(Object[] selection, String item, JTextArea textArea)
    {
        if (selection.length == 0)
            textArea.append("Error \n");
        else
        {
            if("book".equals(item))
            {
                Object o = showDialog("Return a book", selection);
                if (o != null)
                {
                    returnBook(o.toString());
                    textArea.append("Return Successfully \n");
                }
            }
//            else
//                returnMovie(showDialog("Return a movie", selection).toString());
        }
    }

    public void returnBook(String name)
    {
        bookStorage.addBook(borrowedBooks.getBorrowedBookByName(name));
        borrowedBooks.removeBorrowedBook(name);
    }

    public void aboutMe(JTextArea jTextArea)
    {
        jTextArea.append("\n");
        jTextArea.append("Name: " + user.getCustomer().getName() + "\n");
        jTextArea.append("Email: " + user.getCustomer().getEmail() + "\n");
        jTextArea.append("Phone number: " + user.getCustomer().getPhoneNumber() + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == bookList)
            viewBooks(books, textArea);
        else if (e.getSource() == movieList)
            viewMovies(movies, textArea);
        else if (e.getSource() == checkoutBook)
        {
            Object[] selectionBooks = books.toArray();
            checkoutDialogHelper(selectionBooks, "book", textArea);
        }
        else if (e.getSource() == checkoutMovie)
        {
            Object[] selectionMovies = movies.toArray();
            checkoutDialogHelper(selectionMovies, "movie", textArea);
        }
        else if (e.getSource() == returnBook)
        {
            Object[] selectionReturnBook = borrowedBooks.getBorrowedBookList().toArray();
            returnDialogHelper(selectionReturnBook, "book", textArea);
        }
        else if (e.getSource() == aboutMe)
            aboutMe(textArea);
    }

    public BookStorage getBookStorage() {
        return bookStorage;
    }

    public void setBookStorage(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    public MovieStorage getMovieStorage() {
        return movieStorage;
    }

    public void setMovieStorage(MovieStorage movieStorage) {
        this.movieStorage = movieStorage;
    }

    public BorrowedBook getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(BorrowedBook borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public BorrowedMovie getBorrowedMovies() {
        return borrowedMovies;
    }

    public void setBorrowedMovies(BorrowedMovie borrowedMovies) {
        this.borrowedMovies = borrowedMovies;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public Collection<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }
}
