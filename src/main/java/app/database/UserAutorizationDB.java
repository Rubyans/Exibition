package app.database;

import app.database.encrypt.EncryptforDB;
import app.entities.UserAutorization;

import java.sql.*;

public class UserAutorizationDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Connection connAutorization;
    private static Savepoint savepoint;

    public static void startConnnection() { //function creates connect with DB
        if (connAutorization == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connAutorization = DriverManager.getConnection(url);
                    connAutorization.setAutoCommit(false);
                    savepoint = connAutorization.setSavepoint("savepointMain");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection checkConnection() {
        return connAutorization;
    }

    public static void nullConnection() {
        connAutorization = null;
    } //function gives a value of null
    public static UserAutorization authorizationuser(String login, String password) { //functuon select data with user
        Integer userId = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        final String hashPassword = EncryptforDB.encrypt(password);
        String role = "false";
        try {
            PreparedStatement statement = connAutorization.prepareStatement("SELECT user_id,first_name,last_name,email,role from exhibitiondb.authorized_user where login = ? and password = ?");
            statement.setString(1, login);
            statement.setString(2, hashPassword);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt(1);
                firstName = resultSet.getString(2);
                lastName = resultSet.getString(3);
                email = resultSet.getString(4);
                role = resultSet.getString(5);
            }
            statement.close();
            return new UserAutorization(userId, firstName, lastName, login, hashPassword, email, role);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static boolean exitConnection() { //function closes connect with DB
        try {
            if (savepoint != null) {
                connAutorization.close();
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
