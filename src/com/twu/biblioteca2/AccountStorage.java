package com.twu.biblioteca2;

import com.twu.biblioteca.BorrowedBook;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountStorage
{
    private static Map<String, Account> accountList;

    static
    {
        accountList = new HashMap<String, Account>();

        Account account1 = new Account("1111-111", "11111111", new Customer("customer1", "abc@gmail.com", 123456), new BorrowedBook(), new BorrowedMovie());
        Account account2 = new Account("2222-222", "11111111", new Customer("customer2", "def@gmail.com", 123456), new BorrowedBook(), new BorrowedMovie());
        Account account3 = new Account("3333-333", "11111111", new Customer("customer3", "ghi@gmail.com", 123456), new BorrowedBook(), new BorrowedMovie());

        accountList.put("1111-111", account1);
        accountList.put("2222-222", account2);
        accountList.put("3333-333", account3);
    }

    public Collection<Account> getAccountList()
    {
        return accountList.values();
    }

    public void removeAccount(String name)
    {
        accountList.remove(name);
    }

    public void addAccount(Account account)
    {
        accountList.put(account.getCustomer().getName(), account);
    }

    public Account getAccountByName(String name)
    {
        return accountList.get(name);
    }
}
