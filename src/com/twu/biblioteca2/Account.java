package com.twu.biblioteca2;

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
}
