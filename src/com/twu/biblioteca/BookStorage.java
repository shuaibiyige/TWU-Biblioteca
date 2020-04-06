package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BookStorage
{
    private static List<Book> bookList;

    static
    {
        bookList = new ArrayList<Book>();

        Book book1 = new Book("book1", "Patricia Aakhus", 1990);
        Book book2 = new Book("book2", "Lynn Abbey", 1945);
        Book book3 = new Book("book3", "Megan Abbott", 1996);

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
    }

    public static List<Book> getBookList()
    {
        return bookList;
    }
}
