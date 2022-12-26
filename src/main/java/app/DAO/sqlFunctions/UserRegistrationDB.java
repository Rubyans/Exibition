package app.DAO.sqlFunctions;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.sqlFunctions.encrypt.EncryptforDB;
import app.DAO.entities.UserRegistration;
import org.apache.log4j.Logger;

import java.sql.*;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.UserRegistration.INSERT_USER_AUTORIZED;

public class UserRegistrationDB {
    private static final Logger LOGGER = Logger.getLogger(UserRegistrationDB.class);


    public static UserRegistration registrationDB(String firstName, String lastName, String email, String login, String password) { //function insert users data in DB
        final String HASH = EncryptforDB.encrypt(password);
        try {
            Connection connRegistration = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connRegistration.setSavepoint("SaveAdd");
            try {
                PreparedStatement statement = connRegistration.prepareStatement(INSERT_USER_AUTORIZED);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, login);
                statement.setString(4, HASH);
                statement.setString(5, email);
                statement.setInt(6, 1);
                statement.setInt(7, 1);
                statement.execute();
                statement.close();
                LOGGER.debug("registrationDB in debug");
                HikariConnectDB.saveCommit();
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
