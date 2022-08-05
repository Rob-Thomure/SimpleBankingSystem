package org.example;

public class UserAuthentication {
    private static UserAuthentication userAuthentication_instance = null;
    private String userAccountNum;
    private String userPin;
    private boolean loggedIn;

    private UserAuthentication() {
        this.loggedIn = false;
    }

    public static UserAuthentication getInstance() {
        if (userAuthentication_instance == null) {
            userAuthentication_instance = new UserAuthentication();
        }
        return userAuthentication_instance;
    }

    public void logIn(String userAccountNum, String userPIN) {
        Data data = Data.getInstance();
        Account account = data.getAccount(userAccountNum);
        this.userAccountNum = userAccountNum;
        this.userPin = userPIN;
        loggedIn = (account != null && account.getPin().equals(userPin));
    }

    public void logOut() {
        this.userAccountNum = "";
        this.userPin = "";
        loggedIn = false;
    }

    public String getUserAccountNum() {
        return userAccountNum;
    }

    public String getUserPin() {
        return userPin;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
