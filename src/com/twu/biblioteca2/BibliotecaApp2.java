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

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class BibliotecaApp2 implements ActionListener
{
    private JFrame jFrame;
    private JTextArea textArea;
    private JButton bookList, movieList, checkoutBook, returnBook, returnMovie, aboutMe, checkoutMovie, logout;
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

        while (!login())
            login();
        showWelcome(textArea);
    }

    public void initUI()
    {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel jPanelTop = new JPanel();
        JPanel jPanelBottom = new JPanel();
        GridLayout layoutBottom = new GridLayout(4, 2);

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
        returnMovie = new JButton("Return a movie");
        aboutMe = new JButton("About me");
        logout = new JButton("Log out");

        bookList.addActionListener(this);
        movieList.addActionListener(this);
        checkoutBook.addActionListener(this);
        checkoutMovie.addActionListener(this);
        returnBook.addActionListener(this);
        returnMovie.addActionListener(this);
        aboutMe.addActionListener(this);
        logout.addActionListener(this);

        jPanelTop.add(jScrollPane);
        jPanelBottom.add(bookList);
        jPanelBottom.add(movieList);
        jPanelBottom.add(checkoutBook);
        jPanelBottom.add(checkoutMovie);
        jPanelBottom.add(returnBook);
        jPanelBottom.add(returnMovie);
        jPanelBottom.add(aboutMe);
        jPanelBottom.add(logout);

        jPanelBottom.setLayout(layoutBottom);

        jFrame.getContentPane().add(BorderLayout.CENTER, jPanelTop);
        jFrame.getContentPane().add(BorderLayout.SOUTH, jPanelBottom);
        jFrame.setSize(650, 500);
        jFrame.setVisible(true);
    }

    public boolean login()
    {
        boolean result = false;

        Map<String, String> input = CustomizedDialog.showCustomDialog(jFrame, jFrame);       // get user input
        String number = input.get("libraryNumber");
        String password = input.get("password");

        AccountStorage accountStorage = new AccountStorage();
        Collection<Account> accountList = accountStorage.getAccountList();                   // get all accounts

        for (Account account: accountList)
        {
            if (account.getLibraryNumber().equals(number) && account.getPassword().equals(password))
            {
                user = accountStorage.getAccountByName(number);                             // login
                borrowedBooks = user.getBorrowedBook();
                borrowedMovies = user.getBorrowedMovie();
                result = true;
            }
        }
        if (!result)
            loginFailedMessage();
        return result;
    }

    public void loginFailedMessage()
    {
        JOptionPane.showMessageDialog(jFrame, "Library number or password is wrong", "Login Failed", JOptionPane.WARNING_MESSAGE);
    }

    public void logout()
    {
        user = null;
    }

    public void showWelcome(JTextArea jTextArea)
    {
        jTextArea.append("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore! \n");
        jTextArea.append("What do you want? \n");
    }

    public void viewBooks(Collection<Book> bookList, JTextArea jTextArea)
    {
        jTextArea.append("\n");
        jTextArea.append("Name,  Author,  Published Year \n");
        jTextArea.append("-------------------------------\n");
        for(Book book: bookList) {
            jTextArea.append(book.getName() + ",  " + book.getAuthor() + ",  " + book.getPublishedYear() + "\n");
        }
    }

    public void viewMovies(Collection<Movie> movieList, JTextArea jTextArea)
    {
        jTextArea.append("\n");
        jTextArea.append("Name,  Year,  Director,  Rating \n");
        jTextArea.append("--------------------------------\n");
        for(Movie movie: movieList) {
            jTextArea.append(movie.getName() + ",  " + movie.getYear() + ",  " + movie.getDirector() + ",  " + movie.getRating() + "\n");
        }
    }

    public void checkoutBook(String name)
    {
        borrowedBooks.addBorrowedBook(bookStorage.getBookByName(name));      // add to borrowed book list
        user.setBorrowedBook(borrowedBooks);
        bookStorage.removeBook(name);
    }

    public void checkoutMovie(String name)
    {
        borrowedMovies.addBorrowedMovie(movieStorage.getMovieByName(name));      // add to borrowed movie list
        user.setBorrowedMovie(borrowedMovies);
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
            textArea.append("You didn't borrow any " + item + " \n");
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
            else
            {
                Object o = showDialog("Return a movie", selection);
                if (o != null)
                {
                    returnMovie(o.toString());
                    textArea.append("Return Successfully \n");
                }
            }
        }
    }

    public void returnBook(String name)
    {
        bookStorage.addBook(borrowedBooks.getBorrowedBookByName(name));
        borrowedBooks.removeBorrowedBook(name);
    }

    public void returnMovie(String name)
    {
        movieStorage.addMovie(borrowedMovies.getBorrowedMovieByName(name));
        borrowedMovies.removeBorrowedMovie(name);
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
        else if (e.getSource() == returnMovie)
        {
            Object[] selectionReturnMovie = borrowedMovies.getBorrowedMovieList().toArray();
            returnDialogHelper(selectionReturnMovie, "movie", textArea);
        }
        else if (e.getSource() == aboutMe)
            aboutMe(textArea);
        else if (e.getSource() == logout)
        {
            logout();
            while (!login())
            {
                login();
            }
        }
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
