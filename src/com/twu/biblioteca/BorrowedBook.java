package com.twu.biblioteca;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BorrowedBook
{
    private static Map<String, Book> borrowedBookList;

    static {
        borrowedBookList = new HashMap<String, Book>();
    }

    public Collection<Book> getBorrowedBookList()
    {
        return borrowedBookList.values();
    }

    public void removeBorrowedBook(String name)
    {
        borrowedBookList.remove(name);
    }

    public void addBorrowedBook(Book book)
    {
        borrowedBookList.put(book.getName(), book);
    }

    public Book getBorrowedBookByName(String name)
    {
        return borrowedBookList.get(name);
    }
}
