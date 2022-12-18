package app.database;

import app.database.encrypt.EncryptforDB;
import app.entities.UserRegistration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserRegistrationDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Connection connRegistration;
    private static Savepoint savepoint;
    private static final Logger LOGGER = LogManager.getLogger(UserRegistrationDB.class);

    public static void startConnnection() { //function creates connect with DB
        if (connRegistration == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connRegistration = DriverManager.getConnection(url);
                    connRegistration.setAutoCommit(false);
                    savepoint = connRegistration.setSavepoint("savepointMain");
                    LOGGER.debug("startConnection in debug");
                } catch (SQLException e) {
                    LOGGER.error("startConnection " + e.getMessage());
                }
            } catch (Exception e) {
                LOGGER.error("startConnection " + e.getMessage());
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

    public static UserRegistration registrationDB(String firstName, String lastName, String email, String login, String password) { //function insert users data in DB
        final String HASH = EncryptforDB.encrypt(password);
        try {
            Savepoint savepointAdd = connRegistration.setSavepoint("SaveAdd");
            try {
                PreparedStatement statement = connRegistration.prepareStatement("INSERT exhibitiondb.authorized_user(first_name, last_name, login, password, email, role) VALUES (?,?,?,?,?,?)");
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, login);
                statement.setString(4, HASH);
                statement.setString(5, email);
                statement.setInt(6, 1);
                statement.execute();
                statement.close();
                LOGGER.debug("registrationDB in debug");
                return new UserRegistration(true);
            } catch (Exception e) {
                LOGGER.debug("registrationDB " + e.getMessage());
                connRegistration.rollback(savepointAdd);
            }
        } catch (Exception ex) {
            LOGGER.debug("registrationDB " + ex.getMessage());
        }
        return new UserRegistration(false);

    }


}
