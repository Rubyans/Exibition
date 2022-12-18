package app.database.admin.seventhPage;


import app.entities.adminEntities.seventhPage.UserAutorizedShow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeventhPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connUserAutorized;
    private static final Logger LOGGER = LogManager.getLogger(SeventhPageDB.class);

    public static void startConnnection() { //function creates connect with DB
        if (connUserAutorized == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connUserAutorized = DriverManager.getConnection(url);
                    connUserAutorized.setAutoCommit(false);
                    savepoint = connUserAutorized.setSavepoint("savepointMain");
                    LOGGER.debug("startConnection in debug");
                } catch (SQLException e) {
                    LOGGER.error("startConnnection " + e.getMessage());
                }
            } catch (Exception ex) {
                LOGGER.error("startConnnection " + ex.getMessage());
            }
        }
    }

    public static Connection checkConnection() {
        return connUserAutorized;
    }

    public static void nullConnection() {
        connUserAutorized = null;
    }

    public static List<UserAutorizedShow> userAuto() { //function shows userAutorized
        try {
            try {
                List<UserAutorizedShow> userAuto = new ArrayList<>();
                String fistName, lastName, login, password, email, role,access;
                Double amount;

                PreparedStatement statement = connUserAutorized.prepareStatement("SELECT first_name,last_name,login,password,email,amount,role,access FROM exhibitiondb.authorized_user", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setUser = statement.executeQuery();
                while (setUser.next()) {
                    fistName = setUser.getString(1);
                    lastName = setUser.getString(2);
                    login = setUser.getString(3);
                    password = setUser.getString(4);
                    email = setUser.getString(5);
                    amount = setUser.getDouble(6);
                    role = setUser.getString(7);
                    access=setUser.getString(8);
                    userAuto.add(new UserAutorizedShow(fistName, lastName, login, password, email, amount, role,access));
                }
                statement.close();
                LOGGER.debug("userAuto in debug");
                return userAuto;
            } catch (Exception e) {
                LOGGER.error("userAuto " + e.getMessage());
            }
        } catch (Exception ex) {
            LOGGER.error("userAuto " + ex.getMessage());
        }
        return null;
    }

    public static Boolean userAutoAdd(String fistName, String lastName, String login, String password, String email, Double amount, String role) { //function adds UserAutorized
        try {
            Savepoint savepointAdd = connUserAutorized.setSavepoint("SavepointAdd");
            try {
                PreparedStatement UserAdd = connUserAutorized.prepareStatement("INSERT into exhibitiondb.authorized_user (first_name,last_name,login,password,email,amount,role) values (?,?,?,?,?,?,?)");
                UserAdd.setString(1, fistName);
                UserAdd.setString(2, lastName);
                UserAdd.setString(3, login);
                UserAdd.setString(4, password);
                UserAdd.setString(5, email);
                UserAdd.setDouble(6, amount);
                UserAdd.setString(7, role);
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
            Savepoint SavepointAmount = connUserAutorized.setSavepoint("SavepointAmount");
            try {
                PreparedStatement AmountAdd = connUserAutorized.prepareStatement("UPDATE exhibitiondb.authorized_user SET amount = ? WHERE email = ?");
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
            Savepoint savepointDel = connUserAutorized.setSavepoint("SavepointDel");
            try {
                PreparedStatement userDel = connUserAutorized.prepareStatement("DELETE FROM exhibitiondb.authorized_user WHERE email=?");
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

    public static Boolean AccessFirstPage(String emailUser, String access) { //function access users data
        try {
            Savepoint savepointAccess = connUserAutorized.setSavepoint("SavepointAccess");
            try {

                PreparedStatement statementChange = connUserAutorized.prepareStatement("UPDATE exhibitiondb.authorized_user SET access = ? WHERE email = ?");
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

    public static boolean exitConnection() { //function closes connect
        try {
            if (savepoint != null) {
                connUserAutorized.rollback(savepoint);
                connUserAutorized.commit();
                connUserAutorized.close();
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

    public static void saveCommit() { //function saves data
        try {
            connUserAutorized.commit();
            savepoint = connUserAutorized.setSavepoint("savepointMain");
            LOGGER.debug("saveCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("saveCommit " + e.getMessage());
        }
    }

    public static void RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                connUserAutorized.rollback(savepoint);
            LOGGER.debug("RoleBackCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit " + e.getMessage());
        }
    }
}
