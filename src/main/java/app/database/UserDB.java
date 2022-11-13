package app.database;

import app.database.Encrypt.EncryptforDB;
import app.entities.User;

import java.sql.*;

public class UserDB {

    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";


    public static User authorizationuser(String login, String password) {
        String firstName = null;
        String lastName = null;
        String email = null;
        final String hashPassword = EncryptforDB.encrypt(password);
        String role = "false";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url))
            {


                PreparedStatement statement = conn.prepareStatement("select first_name,last_name,email,role from exhibitiondb.authorized_user where login = ? and password = ?");
                statement.setString(1, login);
                statement.setString(2, hashPassword);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    firstName = resultSet.getString(1);
                    lastName = resultSet.getString(2);
                    email = resultSet.getString(3);
                    role = resultSet.getString(4);
                }
                statement.close();
                conn.close();
                return new User(firstName, lastName, login, hashPassword, email, role);
            }
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    public static String registrationDB(String firstName, String lastName, String email, String login, String password) {
        final String hashPassword = EncryptforDB.encrypt(password);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url)) {

                PreparedStatement statement = conn.prepareStatement("INSERT exhibitiondb.authorized_user(first_name, last_name, login, password, email, role) VALUES (?,?,?,?,?,?)");
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, login);
                statement.setString(4, hashPassword);
                statement.setString(5, email);
                statement.setInt(6, 1);
                statement.execute();
                statement.close();
                conn.close();
                return "true";
            }
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return "false";

    }


}
