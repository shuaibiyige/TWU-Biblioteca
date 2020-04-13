package com.twu.biblioteca2;

import java.util.Objects;

public class Customer
{
    private String name;
    private String email;
    private int phoneNumber;

    public Customer(String name, String email, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return phoneNumber == customer.phoneNumber &&
                name.equals(customer.name) &&
                email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber);
    }
}
