package com.twu.biblioteca2;

import com.twu.biblioteca.BorrowedBook;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class AccountStorageTest {

    @Test
    public void shouldContainAListOfAccounts() {
        Collection<Account> actual = new AccountStorage().getAccountList();

        assertThat(actual, hasItems(
                new Account("1111-111", "11111111", new Customer("customer1", "abc@gmail.com", 123456), new BorrowedBook(), new BorrowedMovie()),
                new Account("2222-222", "11111111", new Customer("customer2", "def@gmail.com", 123456), new BorrowedBook(), new BorrowedMovie()),
                new Account("3333-333", "11111111", new Customer("customer3", "ghi@gmail.com", 123456), new BorrowedBook(), new BorrowedMovie())));
    }

    @Test
    public void shouldGetAccountByName()
    {
        Account actual = new AccountStorage().getAccountByName("1111-111");
        assertThat(actual, is(new Account("1111-111", "11111111", new Customer("customer1", "abc@gmail.com", 123456), new BorrowedBook(), new BorrowedMovie())));
    }

    @Test
    public void shouldRemoveAccountByName()
    {
        AccountStorage accountStorage = new AccountStorage();
        Collection<Account> actual = accountStorage.getAccountList();
        accountStorage.removeAccount("1111-111");
        assertThat(actual, not(hasItems(new Account("1111-111", "11111111", new Customer("customer1", "abc@gmail.com", 123456), new BorrowedBook(), new BorrowedMovie()))));
    }
}