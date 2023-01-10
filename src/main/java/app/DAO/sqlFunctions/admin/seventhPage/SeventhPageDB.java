package app.DAO.sqlFunctions.admin.seventhPage;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.seventhPage.UserAutorizedShow;
import app.service.encrypt.Encrypt;
import org.apache.log4j.Logger;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.SeventhPageAdmin.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeventhPageDB {
    private static final Logger LOGGER = Logger.getLogger(SeventhPageDB.class);

    public static List<UserAutorizedShow> userAuto(String valueRows) { //function shows userAutorized
        try {
            Connection connUserAutorized = HikariConnectDB.getConnection();
            List<UserAutorizedShow> userAuto = new ArrayList<>();
            String fistName, lastName, login, password, email, role, access;
            Double amount;
            Integer countRows = 0;
            PreparedStatement statement = connUserAutorized.prepareStatement(SELECT_USER_AUTHORIZED);
            ResultSet setUser = statement.executeQuery();
            while (setUser.next()) {
                fistName = setUser.getString(1);
                lastName = setUser.getString(2);
                login = setUser.getString(3);
                password = setUser.getString(4);
                email = setUser.getString(5);
                amount = setUser.getDouble(6);
                role = setUser.getString(7);
                access = setUser.getString(8);
                if (valueRows.equals("all"))
                    userAuto.add(new UserAutorizedShow(fistName, lastName, login, Encrypt.decryptText(password), email, amount, role, access));
                else {
                    if (countRows < Integer.parseInt(valueRows)) {
                        userAuto.add(new UserAutorizedShow(fistName, lastName, login, Encrypt.decryptText(password), email, amount, role, access));
                        countRows++;
                    }
                }
            }
            statement.close();
            LOGGER.debug("userAuto in debug");
            return userAuto;
        } catch (Exception e) {
            LOGGER.error("userAuto " + e.getMessage());
        }
        return null;
    }

    public static Boolean userAutoAdd(String fistName, String lastName, String login, String password, String email, Double amount, String role) { //function adds UserAutorized
        try {
            Connection connUserAutorized = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connUserAutorized.setSavepoint("SavepointAdd");
            try {
                PreparedStatement UserAdd = connUserAutorized.prepareStatement(INSERT_USER_AUTHORIZED);
                UserAdd.setString(1, fistName);
                UserAdd.setString(2, lastName);
                UserAdd.setString(3, login);
                UserAdd.setString(4, password);
                UserAdd.setString(5, email);
                UserAdd.setDouble(6, amount);
                UserAdd.setString(7, role);
                UserAdd.setInt(8, 1);
                UserAdd.execute();
                UserAdd.close();
                LOGGER.debug("UserAutoAdd in debug");
                return true;
            } catch (Exception e) {
                LOGGER.error("UserAutoAdd " + e.getMessage());
                connUserAutorized.rollback(savepointAdd);
            }
        } catch (Exception ex) {
            LOGGER.error("UserAutoAdd " + ex.getMessage());
        }
        return false;
    }

    public static Boolean userAmountAdd(String email, Double amount) { //function adds Amount for UserAutorized
        try {
            Connection connUserAutorized = HikariConnectDB.getConnection();
            Savepoint SavepointAmount = connUserAutorized.setSavepoint("SavepointAmount");
            try {
                PreparedStatement AmountAdd = connUserAutorized.prepareStatement(UPDATE_USER_AUTHORIZED);
                AmountAdd.setDouble(1, amount);
                AmountAdd.setString(2, email);
                AmountAdd.execute();
                AmountAdd.close();
                LOGGER.debug("userAmountAdd in debug");
                return true;
            } catch (Exception e) {
                LOGGER.error("userAmountAdd " + e.getMessage());
                connUserAutorized.rollback(SavepointAmount);
            }
        } catch (Exception ex) {
            LOGGER.error("userAmountAdd " + ex.getMessage());
        }
        return false;
    }

    public static Boolean userAutoDel(String email) { //function deletes userAutorized data
        try {
            Connection connUserAutorized = HikariConnectDB.getConnection();
            Savepoint savepointDel = connUserAutorized.setSavepoint("SavepointDel");
            try {
                PreparedStatement userDel = connUserAutorized.prepareStatement(DELETE_USER_AUTHORIZED);
                userDel.setString(1, email);
                Integer row = userDel.executeUpdate();
                userDel.close();

                if (row > 0) {
                    LOGGER.debug("userAutoDel in debug");
                    return true;
                }
                LOGGER.debug("userAutoDel in debug");
                return false;
            } catch (Exception e) {
                LOGGER.error("userAutoDel " + e.getMessage());
                connUserAutorized.rollback(savepointDel);
            }
        } catch (Exception e) {
            LOGGER.error("userAutoDel " + e.getMessage());
        }
        return false;
    }

    public static Boolean accessFirstPage(String emailUser, String access) { //function access users data
        try {
            Connection connUserAutorized = HikariConnectDB.getConnection();
            Savepoint savepointAccess = connUserAutorized.setSavepoint("SavepointAccess");
            try {
                PreparedStatement statementChange = connUserAutorized.prepareStatement(UPDATE_USER_AUTHORIZED_ACCESS);
                statementChange.setString(1, access);
                statementChange.setString(2, emailUser);
                Integer row = statementChange.executeUpdate();
                statementChange.close();

                if (row > 0) {
                    LOGGER.debug("AccessFirstPage in debug");
                    return true;
                }
                LOGGER.debug("AccessFirstPage in debug");
                return false;
            } catch (Exception e) {
                LOGGER.error("AccessFirstPage " + e.getMessage());
                connUserAutorized.rollback(savepointAccess);
            }
        } catch (Exception ex) {
            LOGGER.error("AccessFirstPage " + ex.getMessage());
        }
        return false;
    }
}
