package app.database.admin.sixthPage;

import app.entities.adminEntities.sixthPage.ViewShow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SixthPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connView;
    private static final Logger LOGGER = LogManager.getLogger(SixthPageDB.class);

    public static void startConnnection() { //function creates connect with DB
        if (connView == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connView = DriverManager.getConnection(url);
                    connView.setAutoCommit(false);
                    savepoint = connView.setSavepoint("savepointMain");
                    LOGGER.debug("startConnnection in debug");
                } catch (SQLException e) {
                    LOGGER.error("startConnnection " + e.getMessage());
                }
            } catch (Exception ex) {
                LOGGER.error("startConnnection " + ex.getMessage());
            }
        }
    }

    public static Connection checkConnection() {
        return connView;
    }

    public static void nullConnection() { //function gives a value of null
        connView = null;
    }

    public static List<ViewShow> viewShow() { //function shows views data
        try {
            try {
                List<ViewShow> view = new ArrayList<>();
                String name;
                Integer viewId;

                PreparedStatement statement = connView.prepareStatement("SELECT * FROM exhibitiondb.view_art", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setView = statement.executeQuery();
                while (setView.next()) {
                    viewId = setView.getInt(1);
                    name = setView.getString(2);
                    view.add(new ViewShow(viewId, name));
                }
                statement.close();
                LOGGER.debug("viewShow in debug");
                return view;
            } catch (Exception e) {
                LOGGER.error("viewShow " + e.getMessage());
            }
        } catch (Exception ex) {
            LOGGER.error("viewShow " + ex.getMessage());
        }
        return null;
    }

    public static Boolean viewAdd(String name) { //function adds views data
        try {
            Savepoint savepointAdd = connView.setSavepoint("SavepointAdd");
            try {
                PreparedStatement viewAdd = connView.prepareStatement("INSERT into exhibitiondb.view_art (nameview) values (?)");
                viewAdd.setString(1, name);
                viewAdd.execute();
                viewAdd.close();
                LOGGER.debug("viewAdd in debug");
                return true;
            } catch (Exception e) {
                LOGGER.error("viewAdd " + e.getMessage());
                connView.rollback(savepointAdd);
            }
        } catch (Exception ex) {
            LOGGER.error("viewAdd " + ex.getMessage());
        }
        return false;
    }

    public static Boolean viewDel(String email) { //function deletes views data
        try {
            Savepoint savepointDel = connView.setSavepoint("SavepointDel");
            try {
                PreparedStatement viewDel = connView.prepareStatement("DELETE FROM exhibitiondb.view_art WHERE nameview=?");
                viewDel.setString(1, email);
                Integer row = viewDel.executeUpdate();
                viewDel.close();
                if (row > 0) {
                    LOGGER.debug("viewAdd in debug");
                    return true;
                }
                LOGGER.debug("viewAdd in debug");
                return false;
            } catch (Exception e) {
                LOGGER.error("viewAdd " + e.getMessage());
                connView.rollback(savepointDel);
            }
        } catch (Exception ex) {
            LOGGER.error("viewAdd " + ex.getMessage());
        }
        return false;
    }

    public static boolean exitConnection() { //function closes connect with DB
        try {
            if (savepoint != null) {
                connView.rollback(savepoint);
                connView.commit();
                connView.close();
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
            connView.commit();
            savepoint = connView.setSavepoint("savepointMain");
            LOGGER.debug("saveCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("saveCommit " + e.getMessage());
        }

    }

    public static void RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                connView.rollback(savepoint);
            LOGGER.debug("RoleBackCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit " + e.getMessage());
        }

    }
}
