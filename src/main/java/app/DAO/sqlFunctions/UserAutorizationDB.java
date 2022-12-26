package app.DAO.sqlFunctions;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.sqlFunctions.encrypt.EncryptforDB;
import app.DAO.entities.UserAutorization;
import org.apache.log4j.Logger;

import java.sql.*;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.UserAuthorized.SELECT_USER_AUTORIZED;

public class UserAutorizationDB {
    private static final Logger LOGGER = Logger.getLogger(UserAutorizationDB.class);


    public static UserAutorization authorizationUser(String login, String password) { //functuon select data with user
        Integer userId = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String access = null;
        final String HASH = EncryptforDB.encrypt(password);
        String role = "false";
        try {
            Connection connUser = HikariConnectDB.getConnection();
            Savepoint savepointShow = connUser.setSavepoint("savepointShow");
            try {
                PreparedStatement statement = connUser.prepareStatement(SELECT_USER_AUTORIZED);
                statement.setString(1, login);
                statement.setString(2, HASH);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    userId = resultSet.getInt(1);
                    firstName = resultSet.getString(2);
                    lastName = resultSet.getString(3);
                    email = resultSet.getString(4);
                    role = resultSet.getString(5);
                    access = resultSet.getString(6);
                }
                statement.close();
                LOGGER.debug("authorizationUser in debug");
                return new UserAutorization(userId, firstName, lastName, login, HASH, email, role, access);
            } catch (Exception e) {
                LOGGER.error("authorizationUser " + e.getMessage());
                connUser.rollback(savepointShow);
            }
        } catch (Exception ex) {
            LOGGER.error("authorizationUser " + ex.getMessage());
        }
        return null;
    }

}
