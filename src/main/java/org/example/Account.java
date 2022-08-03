package org.example;

public class Account {
    private final String cardNum;
    private final String pin;
    private final int balance;

    public Account(String cardNum, String pin) {
        this.cardNum = cardNum;
        this.pin = pin;
        this.balance = 0;
    }

    public Account(String cardNum, String pin, int balance) {
        this.cardNum = cardNum;
        this.pin = pin;
        this.balance = balance;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }
}