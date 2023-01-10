package app.DAO.sqlFunctions.recoveryPassword;

import app.DAO.connectionDAO.HikariConnectDB;
import app.service.encrypt.Encrypt;
import app.service.recoveryPassword.EmailSend;
import app.service.recoveryPassword.GeneratePassword;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.RecoveryUser.SELECT_NAME_RECOVERY;
import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.RecoveryUser.UPDATE_PIN_RECOVERY;
import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.RecoveryUserPassword.*;

public class RecoveryUserDB {
    private static final Logger LOGGER = Logger.getLogger(RecoveryUserDB.class);

    public static boolean recoveryUser(String email) { //function update pin for user in DB
        String password = GeneratePassword.passwordRandom();
        String name = null;
        final String HASH = Encrypt.encryptText(password);
        try {
            Connection connRecovery = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connRecovery.setSavepoint("recoveryAdd");
            try {
                PreparedStatement statementName = connRecovery.prepareStatement(SELECT_NAME_RECOVERY);
                statementName.setString(1, email);
                ResultSet resultSet = statementName.executeQuery();
                while (resultSet.next()) {
                    name = resultSet.getString(1);
                }
                statementName.close();
                if (name == null)
                    return false;
                else {
                    PreparedStatement statementPassword = connRecovery.prepareStatement(UPDATE_PIN_RECOVERY);
                    statementPassword.setString(1, HASH);
                    statementPassword.setString(2, email);
                    statementPassword.execute();
                    statementPassword.close();
                    LOGGER.debug("recoveryUser in debug");
                    HikariConnectDB.saveCommit();
                    EmailSend.mailSender(email, password);
                    return true;
                }
            } catch (Exception e) {
                LOGGER.debug("recoveryUser " + e.getMessage());
                connRecovery.rollback(savepointAdd);
                return false;
            }
        } catch (Exception ex) {
            LOGGER.debug("recoveryUser " + ex.getMessage());
            return false;
        }
    }

    public static boolean recoveryPassword(String pin, String password) { //function update password for user in DB
        try {
            Connection connRecovery = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connRecovery.setSavepoint("recoveryAddPassword");
            final String HASHPIN = Encrypt.encryptText(pin);
            final String HASHPASSWORD = Encrypt.encryptText(password);
            String name = null;
            try {
                PreparedStatement statementName = connRecovery.prepareStatement(SELECT_NAME_PIN_RECOVERY);
                statementName.setString(1, HASHPIN);
                ResultSet resultSet = statementName.executeQuery();
                while (resultSet.next()) {
                    name = resultSet.getString(1);
                }
                statementName.close();
                if (name == null)
                    return false;
                else {
                    PreparedStatement statementPassword = connRecovery.prepareStatement(UPDATE_PASSWORD_PIN_RECOVERY);
                    statementPassword.setString(1, HASHPASSWORD);
                    statementPassword.setString(2, HASHPIN);
                    statementPassword.execute();
                    statementPassword.close();
                    LOGGER.debug("recoveryUser in debug");

                    PreparedStatement statementDel=connRecovery.prepareStatement(DELETE_PIN_RECOVERY);
                    statementDel.setString(1,null);
                    statementDel.setString(2,HASHPASSWORD);
                    statementDel.execute();
                    statementDel.close();
                    HikariConnectDB.saveCommit();
                    return true;
                }
            } catch (Exception e) {
                LOGGER.debug("recoveryUser " + e.getMessage());
                connRecovery.rollback(savepointAdd);
                return false;
            }
        } catch (Exception ex) {
            LOGGER.debug("recoveryUser " + ex.getMessage());
            return false;
        }
    }
}
