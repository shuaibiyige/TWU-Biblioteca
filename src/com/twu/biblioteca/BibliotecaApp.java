package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp
{
    public static void main(String[] args)
    {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.appStart();
    }

    public void appStart()
    {
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
        String choice = console.nextLine();
        return choice;
    }

    public void viewBooks(PrintStream printStream)
    {
        List<Book> bookList = BookStorage.getBookList();
        for(Book book: bookList)
        {
            printStream.println(book.toString());
        }
    }

    public void notifyInvalidMessage(PrintStream printStream)
    {
        printStream.println("Please select a valid option!");
    }
}
