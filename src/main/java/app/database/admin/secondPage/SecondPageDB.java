package app.database.admin.secondPage;

import app.entities.adminEntities.secondPage.HallShow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SecondPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connHall;
    private static final Logger LOGGER = LogManager.getLogger(SecondPageDB.class);
    public static void startConnnection() //function creates connect with DB
    {
        if (connHall == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connHall = DriverManager.getConnection(url);
                    connHall.setAutoCommit(false);
                    savepoint = connHall.setSavepoint("savepointMain");
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
        return connHall;
    }

    public static void nullConnection() {
        connHall = null;
    } //function gives a value of null

    public static List<HallShow> hallShow() { //function show halls data
        try {
            List<HallShow> hall = new ArrayList<>();
            String nameHall = null;
            BigDecimal square = null;

            PreparedStatement statement = connHall.prepareStatement("SELECT name,square FROM exhibitiondb.hall", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setHall = statement.executeQuery();
            while (setHall.next()) {
                nameHall = setHall.getString(1);
                square = setHall.getBigDecimal(2);
                hall.add(new HallShow(nameHall, square));
            }
            statement.close();
            LOGGER.debug("hallShow in debug");
            return hall;
        } catch (Exception e) {
            LOGGER.error("hallShow " + e.getMessage());
        }
        return null;
    }

    public static Boolean hallAdd(String nameExibition, Double square) { //function adds hall data
        try {
            Savepoint savepointAdd = connHall.setSavepoint("SavepointAdd");
            try {
                PreparedStatement HallAdd = connHall.prepareStatement("INSERT into exhibitiondb.hall (name,square) values (?,?)");
                HallAdd.setString(1, nameExibition);
                HallAdd.setDouble(2, square);
                HallAdd.execute();
                HallAdd.close();
                LOGGER.debug("hallShow in debug");
                return true;
            } catch (Exception e) {
                LOGGER.error("hallShow " + e.getMessage());
                connHall.rollback(savepointAdd);
            }
        } catch (Exception e) {
            LOGGER.error("hallShow " + e.getMessage());
        }
        return false;
    }

    public static Boolean hallDel(String nameHall) { //function deletes halls data
        try {
            Savepoint savepointDel = connHall.setSavepoint("SavepointDel");
            try {
                PreparedStatement ExhibitionDel = connHall.prepareStatement("DELETE FROM exhibitiondb.hall WHERE name=?");
                ExhibitionDel.setString(1, nameHall);
                Integer row = ExhibitionDel.executeUpdate();
                ExhibitionDel.close();

                if (row > 0) {
                    LOGGER.debug("hallDel in debug");
                    return true;
                }
                LOGGER.debug("hallDel in debug");
                return false;
            } catch (Exception e) {
                LOGGER.error("hallDel " + e.getMessage());
                connHall.rollback(savepointDel);
            }
        } catch (Exception e) {
            LOGGER.error("hallDel " + e.getMessage());
        }
        return false;
    }

    public static boolean exitConnection() { //function closes connect with DB
        try {
            if (savepoint != null) {
                connHall.rollback(savepoint);
                connHall.commit();
                connHall.close();
                LOGGER.debug("exitConnection in debug");
                return true;
            }
            LOGGER.debug("exitConnection in debug");
            return false;
        } catch (SQLException e) {
            LOGGER.error("exitConnection "+e.getMessage());
            return false;
        }
    }

    public static void saveCommit() { //function saves data
        try {
            connHall.commit();
            savepoint = connHall.setSavepoint("savepointMain");
            LOGGER.debug("saveCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("saveCommit "+e.getMessage());
        }

    }

    public static void RoleBackCommit() { //function roleback commit(data)
        try {
            if (savepoint != null)
                connHall.rollback(savepoint);
            LOGGER.debug("RoleBackCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit "+e.getMessage());
        }

    }

}
