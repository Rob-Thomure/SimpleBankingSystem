package org.example;

public class App {
    BankingSystem bankingSystem;

    public App() {
        this.bankingSystem = new BankingSystem();
    }

    public void startApp() {
        while (true) {
            int mainChoice = bankingSystem.executeMainMenu();
            switch (mainChoice) {
                case 0 :
                    bankingSystem.exit();
                    break;
                case 1:
                    bankingSystem.createNewAccount();
                    break;
                case 2:
                    if (bankingSystem.login()) {
                        executeAccountMenu();
                    }
                    break;
                default:
                    System.out.println("Invalid entry");
                    break;
            }
        }
    }

    private void executeAccountMenu() {
        while (true) {
            int accountMenuChoice = bankingSystem.executeAccountMenu();
            switch (accountMenuChoice) {
                case 0:
                    bankingSystem.exit();
                    break;
                case 1:
                    bankingSystem.printAccountBalance();
                    break;
                case 2:
                    bankingSystem.logout();
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
