package app.database;

import app.database.encrypt.EncryptforDB;
import app.entities.UserAutorization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserAutorizationDB {
    private static final Logger LOGGER = LogManager.getLogger(UserAutorizationDB.class);
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
                    LOGGER.debug("startConnnection in debug");
                } catch (SQLException e) {
                    LOGGER.error("startConnnection " + e.getMessage());
                }
            } catch (Exception e) {
                LOGGER.error("startConnnection " + e.getMessage());
            }
        }
    }

    public static Connection checkConnection() {
        return connAutorization;
    }

    public static void nullConnection() {
        connAutorization = null;
    } //function gives a value of null

    public static UserAutorization authorizationUser(String login, String password) { //functuon select data with user
        Integer userId = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String access = null;
        final String HASH = EncryptforDB.encrypt(password);
        String role = "false";
        try {
            PreparedStatement statement = connAutorization.prepareStatement("SELECT user_id,first_name,last_name,email,role,access from exhibitiondb.authorized_user where login = ? and password = ?");
            statement.setString(1, login);
            statement.setString(2, HASH);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt(1);
                firstName = resultSet.getString(2);
                lastName = resultSet.getString(3);
                email = resultSet.getString(4);
                role = resultSet.getString(5);
                access=resultSet.getString(6);
            }
            statement.close();
            LOGGER.debug("authorizationUser in debug");
            return new UserAutorization(userId, firstName, lastName, login, HASH, email, role,access);

        } catch (Exception ex) {
            LOGGER.error("authorizationUser " + ex.getMessage());
        }
        return null;
    }

    public static boolean exitConnection() { //function closes connect with DB
        try {
            if (savepoint != null) {
                connAutorization.close();
                LOGGER.debug("exitConnection in debug");
                return true;
            }
            LOGGER.debug("exitConnection in debug");
            return false;
        } catch (SQLException e) {
            LOGGER.error("exitConnection " + e.getMessage());
            return false;
        }
    }
}
