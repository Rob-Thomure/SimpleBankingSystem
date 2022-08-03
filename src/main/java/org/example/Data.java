package org.example;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Data {
    private final String url;
    private final SQLiteDataSource dataSource;
    private static Data data_instance = null;



    private Data() {
        this.url = "jdbc:sqlite:";
        this.dataSource = new SQLiteDataSource();
    }

    public static Data getInstance() {
        if (data_instance == null) {
            data_instance = new Data();
        }
        return data_instance;
    }

    public void setFileName(String fileName) {
        this.dataSource.setUrl(url + fileName);
    }

    public void createTable() {
        try (Connection con = dataSource.getConnection();
                Statement statement = con.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                                        "id INTEGER PRIMARY KEY, " +
                                        "number TEXT, " +
                                        "pin TEXT, " +
                                        "balance INTEGER DEFAULT 0);");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCard(String number, String pin) {
        try (Connection con = dataSource.getConnection();
                Statement statement = con.createStatement()) {
            statement.executeUpdate(String.format("INSERT INTO card (number, pin) " +
                                        "VALUES (%s, %s);", number, pin));
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public Account selectCard(String number) {
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(String.format("SELECT number, pin, balance " +
                    "FROM card " +
                    "WHERE number = %s", number))) {
                if (resultSet.next()) {
                    String cardNum = resultSet.getString("number");
                    String pin = resultSet.getString("pin");
                    int balance = resultSet.getInt("balance");
                    return new Account(cardNum, pin, balance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int selectBalance(String number) {
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format("SELECT balance " +
                                                                "FROM card " +
                                                                "WHERE number = %s;", number))
             ) {
            return resultSet.getInt("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
