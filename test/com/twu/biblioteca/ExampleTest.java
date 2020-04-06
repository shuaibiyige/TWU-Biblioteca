package com.twu.biblioteca;


import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExampleTest
{
    @Test
    public void showWelcomeMessage()
    {
        PrintStream printStream = mock(PrintStream.class);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.showWelcome(printStream);

        verify(printStream).println("Welcome to Biblioteca!!");
    }
}
