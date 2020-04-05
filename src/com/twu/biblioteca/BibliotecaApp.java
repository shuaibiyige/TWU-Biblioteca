package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp
{

    public static void main(String[] args)
    {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        Scanner console = new Scanner(System.in);

        bibliotecaApp.showWelcome();
    }

    public void showWelcome()
    {
        System.out.println("Welcome to Biblioteca!!");
    }
}
