package com.twu.biblioteca;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookStorage
{
    private static Map<String, Book> bookList;

    static
    {
        bookList = new HashMap<String, Book>();

        Book book1 = new Book("book1", "Patricia Aakhus", 1990);
        Book book2 = new Book("book2", "Lynn Abbey", 1945);
        Book book3 = new Book("book3", "Megan Abbott", 1996);

        bookList.put("book1", book1);
        bookList.put("book2", book2);
        bookList.put("book3", book3);
    }

    public Collection<Book> getBookList()
    {
        return bookList.values();
    }

    public void removeBook(String name)
    {
        bookList.remove(name);
    }

    public void addBook(Book book)
    {
        bookList.put(book.getName(), book);
    }

    public Book getBookByName(String name)
    {
        return bookList.get(name);
    }
}
