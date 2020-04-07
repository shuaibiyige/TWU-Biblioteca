package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

public class BibliotecaApp
{
    private BookStorage bookStorage;
    private Collection<Book> bookList;

    public static void main(String[] args)
    {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.appStart();
    }

    public void appStart()
    {
        bookStorage = new BookStorage();
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
            if ("b".equalsIgnoreCase(UserInput))
                checkout(console);                       // check out a book by typing the name of the book
            if ("c".equalsIgnoreCase(UserInput))
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
        printStream.println("B. Quit");
    }

    public String getUserInput(Scanner console)
    {
        return console.nextLine();
    }

    public void viewBooks(PrintStream printStream)
    {
        bookList = bookStorage.getBookList();
        for(Book book: bookList)
        {
            printStream.println(book.toString());
        }
    }

    public void notifyInvalidMessage(PrintStream printStream)
    {
        printStream.println("Please select a valid option!");
    }

    public int checkout(Scanner console)
    {
        String name = console.nextLine();
        if (bookList.contains(bookStorage.getBookByName(name)))
        {
            bookStorage.removeBook(name);
            return 0;                       // remove successfully
        }
        else
            return 1;                       // remove unsuccessfully
    }
}
