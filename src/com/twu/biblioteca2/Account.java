package com.twu.biblioteca2;

import java.util.Objects;

public class Account
{
    private String libraryNumber;
    private String password;
    private Customer customer;

    public Account(String libraryNumber, String password, Customer customer) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.customer = customer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return libraryNumber.equals(account.libraryNumber) &&
                password.equals(account.password) &&
                customer.equals(account.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryNumber, password, customer);
    }
}
