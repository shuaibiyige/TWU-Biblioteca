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

        showWelcome(printStream);
        while (true)
            if ("a".equalsIgnoreCase(showMenu(printStream, System.in)))
                viewBooks(printStream);
    }

    public void showWelcome(PrintStream printStream)
    {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public String showMenu(PrintStream printStream, InputStream inputStream)
    {
        printStream.println("A. List of books");
        String choice = new Scanner(System.in).nextLine();
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
}
