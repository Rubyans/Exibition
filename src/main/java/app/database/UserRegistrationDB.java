package app.database;

import app.database.encrypt.EncryptforDB;
import app.entities.UserRegistration;

import java.sql.*;

public class UserRegistrationDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Connection connRegistration;
    private static Savepoint savepoint;

    public static void startConnnection() { //function creates connect with DB
        if (connRegistration == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connRegistration = DriverManager.getConnection(url);
                    connRegistration.setAutoCommit(false);
                    savepoint = connRegistration.setSavepoint("savepointMain");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection checkConnection() {
        return connRegistration;
    }

    public static void nullConnection() {
        connRegistration = null;
    } //function gives a value of null

    public static boolean exitConnection() { //function closes connect with DB
        try {
            if (savepoint != null) {
                connRegistration.commit();
                connRegistration.close();
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static UserRegistration registrationDB(String firstName, String lastName, String email, String login, String password) { //function insert users data in DB
        final String hashPassword = EncryptforDB.encrypt(password);
        try {
            Savepoint savepointAdd = connRegistration.setSavepoint("SaveAdd");
            try {
                PreparedStatement statement = connRegistration.prepareStatement("INSERT exhibitiondb.authorized_user(first_name, last_name, login, password, email, role) VALUES (?,?,?,?,?,?)");
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, login);
                statement.setString(4, hashPassword);
                statement.setString(5, email);
                statement.setInt(6, 1);
                statement.execute();
                statement.close();
                return new UserRegistration(true);
            } catch (Exception e) {
                e.printStackTrace();
                connRegistration.rollback(savepointAdd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new UserRegistration(false);

    }


}
