package org.example;

public class Main {
    public static void main(String[] args) {
        String fileName = args[1];
        Data data = Data.getInstance();
        data.setFileName(fileName);
        data.createTable();
        App app = new App();
        app.startApp();
    }
}