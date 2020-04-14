package com.twu.biblioteca2;

import com.twu.biblioteca.BorrowedBook;
import org.junit.Test;

import java.util.Objects;

public class Account
{
    private String libraryNumber;
    private String password;
    private Customer customer;
    private BorrowedBook borrowedBook;
    private BorrowedMovie borrowedMovie;

    public Account(String libraryNumber, String password, Customer customer, BorrowedBook borrowedBook, BorrowedMovie borrowedMovie) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.customer = customer;
        this.borrowedBook = borrowedBook;
        this.borrowedMovie = borrowedMovie;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BorrowedBook getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(BorrowedBook borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public BorrowedMovie getBorrowedMovie() {
        return borrowedMovie;
    }

    public void setBorrowedMovie(BorrowedMovie borrowedMovie) {
        this.borrowedMovie = borrowedMovie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return libraryNumber.equals(account.libraryNumber) &&
                password.equals(account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryNumber, password);
    }
}
