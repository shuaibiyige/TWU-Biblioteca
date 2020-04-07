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
        bookList = bookStorage.getBookList();
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
                if (checkout(bookStorage, bookList, printStream, console) == 0)          // check out successfully
                    printStream.println("Thank you! Enjoy the book");
                else
                    printStream.println("Sorry, that book is not available");      // check out unsuccessfully

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
        printStream.println("B. Check out a book");
        printStream.println("C. Quit");
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

    public int checkout(BookStorage bookStorage, Collection<Book> bookList, PrintStream printStream, Scanner console)
    {
        printStream.println("Please select a book");

        String name = console.nextLine();

        if (isBookNameValid(bookList, name) && bookList.contains(bookStorage.getBookByName(name)))
        {
            bookStorage.removeBook(name);
            return 0;                       // remove successfully
        }
        else
            return 1;                       // remove unsuccessfully
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

    
}
