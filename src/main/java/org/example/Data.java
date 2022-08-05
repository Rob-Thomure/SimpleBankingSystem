package org.example;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

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

    public Account getAccount(String number) {
        String select = "SELECT number, pin, balance " +
                "FROM card " +
                "WHERE number = ?";
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(select)) {
                preparedStatement.setString(1, number);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String cardNum = resultSet.getString("number");
                        String pin = resultSet.getString("pin");
                        int balance = resultSet.getInt("balance");
                        return new Account(cardNum, pin, balance);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getBalance(String number) {
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

    public void addIncome(String number, int deposit) {
        int balance = getBalance(number);
        String update = "UPDATE card " +
                "SET balance = ? " +
                "WHERE number = ?";
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(update)) {
                preparedStatement.setInt(1, balance + deposit);
                preparedStatement.setString(2, number);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String getAccount = "SELECT number, pin, balance " +
            "FROM card " +
            "WHERE number = ?";

    public int getTransfereeBalance(String transfereeUserNum) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(getAccount)) {
                preparedStatement.setString(1, transfereeUserNum);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("balance");
                    } else {
                        return -1;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    String setBalance = "UPDATE card " +
            "SET balance = ? " +
            "WHERE number = ?";

    String deleteAccount = "DELETE " +
            "FROM card " +
            "WHERE number = ?";

    public void deleteAccount(String number) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(deleteAccount)) {
                preparedStatement.setString(1, number);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transferBalance(String userNum, String transfereeNum,int newUserBalance, int newTransfereeBalance) {
        try (Connection con = dataSource.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement preparedStatement = con.prepareStatement(setBalance)) {
                preparedStatement.setInt(1, newUserBalance);
                preparedStatement.setString(2, userNum);
                preparedStatement.executeUpdate();
            }
            try (PreparedStatement preparedStatement = con.prepareStatement(setBalance)) {
                preparedStatement.setInt(1, newTransfereeBalance);
                preparedStatement.setString(2, transfereeNum);
                preparedStatement.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
