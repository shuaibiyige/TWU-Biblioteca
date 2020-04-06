package com.twu.biblioteca;

import java.io.PrintStream;
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
        Scanner console = new Scanner(System.in);
        PrintStream printStream = new PrintStream(System.out);

        showWelcome(printStream);
        showMenu();
    }

    public void showWelcome(PrintStream printStream)
    {
        printStream.println("Welcome to Biblioteca!!");
    }

    /**
     * to do
     */
    public void showMenu()
    {

    }
}
