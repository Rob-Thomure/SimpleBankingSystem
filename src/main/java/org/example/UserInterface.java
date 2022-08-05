package org.example;

import java.util.Scanner;

public class UserInterface {

    public void printMainMenu() {
        System.out.println("1. Create an account\n" +
                "2. Log into account\n" +
                "0. Exit");
    }

    public void printAccountMenu() {
        System.out.println("1. Balance\n" +
                "2. Add income\n" +
                "3. Do transfer\n" +
                "4. Close account\n" +
                "5. Log out\n" +
                "0. Exit");
    }

    public void printNewCard(String cardNum, String pin) {
        System.out.printf("Your card has been created" +
                "\nYour card number:" +
                "\n%s" +
                "\nYour card PIN:" +
                "\n%s" +
                "\n", cardNum, pin);
    }

    public void printFailedLogin() {
        System.out.println("Wrong card number or PIN!\n");
    }

    public void printSuccessfulLogin() {
        System.out.println("You have successfully logged in!\n");
    }

    public void printBalance(int balance) {
        System.out.printf("Balance: %d\n\n", balance);
    }

    public void printLoggedOut() {
        System.out.println("You have successfully logged out!\n");
    }

    public void printExit() {
        System.out.println("Bye!");
    }

    public int getMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        int menuChoice = scanner.nextInt();
        System.out.println();
        return menuChoice;
    }

    public String getCardNum() {
        System.out.println("Enter your card number:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getPIN() {
        System.out.println("Enter your PIN:");
        Scanner scanner = new Scanner(System.in);
        String userPIN = scanner.nextLine();
        System.out.println();
        return userPIN;
    }

    public int enterIncome() {
        System.out.println("Enter income:");
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();
        System.out.println("Income was added!\n");
        return amount;
    }

    public String enterCardNum() {
        System.out.println("Transfer\n" +
                "Enter card number:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void failedLuhn() {
        System.out.println("Probably you made a mistake in the card number. Please try again!\n");
    }

    public void printCardNotExist() {
        System.out.println("Such a card does not exist.\n");
    }

    public int enterTransferAmount() {
        System.out.println("Enter how much money you want to transfer:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
