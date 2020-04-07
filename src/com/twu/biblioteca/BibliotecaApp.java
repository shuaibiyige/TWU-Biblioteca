package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

public class BibliotecaApp
{
    private BookStorage bookStorage;
    private Collection<Book> bookList;
    private BorrowedBook borrowedBooks;

    public static void main(String[] args)
    {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.appStart();
    }

    public void appStart()
    {
        bookStorage = new BookStorage();
        bookList = bookStorage.getBookList();
        borrowedBooks = new BorrowedBook();

        PrintStream printStream = new PrintStream(System.out);
        InputStream inputStream = System.in;
        Scanner console = new Scanner(inputStream);

        showWelcome(printStream);
        while (true)
        {
            showMenu(printStream);
            String UserInput = getUserInput(console);    // get user's choice

            if ("a".equalsIgnoreCase(UserInput))
                viewBooks(printStream);                  // show book list

            else if ("b".equalsIgnoreCase(UserInput))
                if (checkout(bookStorage, borrowedBooks, bookList, printStream, console) == 0)          // check out successfully
                    printStream.println("Thank you! Enjoy the book");
                else
                    printStream.println("Sorry, that book is not available");      // check out unsuccessfully

            else if ("c".equalsIgnoreCase(UserInput))
                if (returnBook(bookStorage, borrowedBooks, printStream, console) == 0)
                    printStream.println("Thank you for returning the book");       // return a book successfully
                else
                    printStream.println("That is not a valid book to return");     // return a book unsuccessfully

            else if ("d".equalsIgnoreCase(UserInput))
                break;                                   // quit

            else
                notifyInvalidMessage(printStream);       // show notification
        }
    }

    public void showWelcome(PrintStream printStream)
    {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void showMenu(PrintStream printStream)
    {
        printStream.println("A. List of books");
        printStream.println("B. Check out a book");
        printStream.println("C. Return a book");
        printStream.println("D. Quit");
    }

    public String getUserInput(Scanner console)
    {
        return console.nextLine();
    }

    public void viewBooks(PrintStream printStream)
    {
        for(Book book: bookList)
        {
            printStream.println(book.toString());
        }
    }

    public void notifyInvalidMessage(PrintStream printStream)
    {
        printStream.println("Please select a valid option!");
    }

    public int checkout(BookStorage bookStorage, BorrowedBook borrowedBooks, Collection<Book> bookList, PrintStream printStream, Scanner console)
    {
        printStream.println("Please select a book");

        String name = console.nextLine();

        if (isBookNameValid(bookList, name) && bookList.contains(bookStorage.getBookByName(name)))
        {
            borrowedBooks.addBorrowedBook(bookStorage.getBookByName(name));      // add to borrowed book list
            bookStorage.removeBook(name);

            return 0;                                                // remove successfully
        }
        else
            return 1;                                                // remove unsuccessfully
    }

    public boolean isBookNameValid(Collection<Book> bookList, String name)
    {
        boolean result = false;
        for (Book book: bookList)
        {
            if (name.equals(book.getName()))
                result = true;
        }
        return result;
    }

    public int returnBook(BookStorage bookStorage, BorrowedBook borrowedBooks, PrintStream printStream, Scanner console)
    {
        printStream.println("Please enter the book's name you want to return");
        String name = console.nextLine();

        if (isBookNameValid(borrowedBooks.getBorrowedBookList(), name))
        {
            bookStorage.addBook(borrowedBooks.getBorrowedBookByName(name));
            borrowedBooks.removeBorrowedBook(name);
            return 0;                                                          // remove successfully
        }
        return 1;                                                              // remove unsuccessfully
    }

    public BookStorage getBookStorage() {
        return bookStorage;
    }

    public Collection<Book> getBookList() {
        return bookList;
    }

    public void setBookStorage(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    public void setBookList(Collection<Book> bookList) {
        this.bookList = bookList;
    }

    public BorrowedBook getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(BorrowedBook borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
