package org.example;

public class BankingSystem {
    UserInterface userInterface;

    public BankingSystem() {
        userInterface = new UserInterface();
    }

    public int executeMainMenu() {
        userInterface.printMainMenu();
        return userInterface.getMenuChoice();
    }

    public void createNewAccount() {
        AccountCreator accountCreator = new AccountCreator();
        String accountNum = accountCreator.generateCardNum();
        String pin = accountCreator.generatePIN();
        Data data = Data.getInstance();
        data.insertCard(accountNum, pin);
        userInterface.printNewCard(accountNum, pin);
    }

    public boolean login() {
        String userCardNum = userInterface.getCardNum();
        String userPIN = userInterface.getPIN();
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        userAuthentication.logIn(userCardNum, userPIN);
        if (userAuthentication.isLoggedIn()) {
            userInterface.printSuccessfulLogin();
        } else {
            userInterface.printFailedLogin();
        }
        return userAuthentication.isLoggedIn();
    }

    public int executeAccountMenu() {
        userInterface.printAccountMenu();
        return userInterface.getMenuChoice();
    }

    public void printAccountBalance() {
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        Data data = Data.getInstance();
        if (userAuthentication.isLoggedIn()) {
            String userAccountNum = userAuthentication.getUserAccountNum();
            int balance = data.selectBalance(userAccountNum);
            userInterface.printBalance(balance);
        }
    }

    public void logout() {
        UserAuthentication userAuthentication = UserAuthentication.getInstance();
        userAuthentication.logOut();
        userInterface.printLoggedOut();
    }

    public void exit() {
        userInterface.printExit();
        System.exit(0);
    }
}