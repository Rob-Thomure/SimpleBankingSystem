package org.example;

public class BankingSystem {
    UserInterface userInterface;
    Data data;
    UserAuthentication userAuthentication;

    public BankingSystem() {
        userInterface = new UserInterface();
        this.data = Data.getInstance();
        this.userAuthentication = UserAuthentication.getInstance();
    }

    public void addIncome() {
        int income = userInterface.enterIncome();
        data.addIncome(userAuthentication.getUserAccountNum(), income);
    }

    public int executeMainMenu() {
        userInterface.printMainMenu();
        return userInterface.getMenuChoice();
    }

    public void createNewAccount() {
        AccountCreator accountCreator = new AccountCreator();
        String accountNum = accountCreator.generateCardNum();
        String pin = accountCreator.generatePIN();
        data.insertCard(accountNum, pin);
        userInterface.printNewCard(accountNum, pin);
    }

    public boolean login() {
        String userCardNum = userInterface.getCardNum();
        String userPIN = userInterface.getPIN();
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
        if (userAuthentication.isLoggedIn()) {
            String userAccountNum = userAuthentication.getUserAccountNum();
            int balance = data.getBalance(userAccountNum);
            userInterface.printBalance(balance);
        }
    }

    public void logout() {
        userAuthentication.logOut();
        userInterface.printLoggedOut();
    }

    public void exit() {
        userInterface.printExit();
        System.exit(0);
    }

    public void closeAccount() {
        String userCardNum = userAuthentication.getUserAccountNum();
        data.deleteAccount(userCardNum);
        System.out.println("The account has been closed!\n");
    }

    public void transfer() {
        String transfereeCardNum = userInterface.enterCardNum();
        String userCardNum = userAuthentication.getUserAccountNum();
        if (transfereeCardNum.equals(userCardNum)) {
            System.out.println("You can't transfer money to the same account!\n");
            return;
        }
        if (LuhnAlgorithm.passesLuhnFormula(transfereeCardNum)) {
            int transfereeBalance = data.getTransfereeBalance(transfereeCardNum);
            if (transfereeBalance != -1) {
                int amount = userInterface.enterTransferAmount();
                int newUserBalance = data.getBalance(userCardNum) - amount;
                if (newUserBalance < 0) {
                    System.out.println("Not enough money!\n");
                    return;
                }
                int newTransfereeBalance = transfereeBalance + amount;
                data.transferBalance(userAuthentication.getUserAccountNum(), transfereeCardNum, newUserBalance, newTransfereeBalance);
                System.out.println("Success!\n");
            } else {
                userInterface.printCardNotExist();
            }
        } else {
            userInterface.failedLuhn();
        }
    }
}