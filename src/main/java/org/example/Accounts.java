package org.example;

import java.util.HashMap;
import java.util.Map;

public class Accounts {

    private static Accounts accounts_instance = null;
    Map<String, Account> accounts;

    private Accounts() {
        this.accounts = new HashMap<>();
    }

    public static Accounts getInstance() {
        if (accounts_instance == null) {
            accounts_instance = new Accounts();
        }
        return accounts_instance;
    }

    public void addAccount(String cardNum, String pin) {
        Account account = new Account(cardNum, pin);
        accounts.put(cardNum, account);
    }

    public Account getAccount(String accountNum) {
        return accounts.getOrDefault(accountNum, null);
    }
}
