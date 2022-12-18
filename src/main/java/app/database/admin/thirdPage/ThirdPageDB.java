package app.database.admin.thirdPage;

import app.entities.adminEntities.thirdPage.AddressShow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThirdPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connAddress;
    private static final Logger LOGGER = LogManager.getLogger(ThirdPageDB.class);

    public static void startConnnection() //function creates connect
    {
        if (connAddress == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connAddress = DriverManager.getConnection(url);
                    connAddress.setAutoCommit(false);
                    savepoint = connAddress.setSavepoint("savepointMain");
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
        return connAddress;
    }

    public static void nullConnection() {
        connAddress = null;
    }

    public static List<AddressShow> addressShow() { //function shows address
        try {
            List<AddressShow> address = new ArrayList<>();

            Integer Unumber;
            String country = null;
            String city = null;
            String street = null;
            Integer numberHouse;

            PreparedStatement statement = connAddress.prepareStatement("SELECT* FROM exhibitiondb.exhibition_address", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setAddress = statement.executeQuery();
            while (setAddress.next()) {
                Unumber = setAddress.getInt(1);
                country = setAddress.getString(2);
                city = setAddress.getString(3);
                street = setAddress.getString(4);
                numberHouse = setAddress.getInt(5);
                address.add(new AddressShow(Unumber, country, city, street, numberHouse));
            }
            statement.close();
            LOGGER.debug("addressShow in debug");
            return address;
        } catch (Exception e) {
            LOGGER.error("addressShow " + e.getMessage());
        }
        return null;
    }

    public static Boolean addressAdd(String country, String city, String street, int numberHouse) { //function adds address data
        try {
            Savepoint savepointAdd = connAddress.setSavepoint("SavepointAdd");
            try {
                PreparedStatement AddressAdd = connAddress.prepareStatement("INSERT into exhibitiondb.exhibition_address (country,city,street_or_square,number_home) values (?,?,?,?)");
                AddressAdd.setString(1, country);
                AddressAdd.setString(2, city);
                AddressAdd.setString(3, street);
                AddressAdd.setInt(4, numberHouse);
                AddressAdd.execute();
                AddressAdd.close();
                LOGGER.debug("addressAdd in debug");
                return true;
            } catch (Exception e) {
                LOGGER.error("addressAdd " + e.getMessage());
                connAddress.rollback(savepointAdd);
            }
        } catch (Exception ex) {
            LOGGER.error("addressAdd " + ex.getMessage());
        }
        return false;
    }

    public static Boolean addressDel(Integer Unumber) { //function deletes address data
        try {
            Savepoint savepointDel = connAddress.setSavepoint("SavepointDel");
            try {
                PreparedStatement AddressDel = connAddress.prepareStatement("DELETE FROM exhibitiondb.exhibition_address WHERE address_id=?");
                AddressDel.setInt(1, Unumber);
                Integer row = AddressDel.executeUpdate();
                AddressDel.close();

                if (row > 0) {
                    LOGGER.debug("addressDel in debug");
                    return true;
                }
                LOGGER.debug("addressDel in debug");
                return false;
            } catch (Exception e) {
                LOGGER.error("addressDel " + e.getMessage());
                connAddress.rollback(savepointDel);
            }
        } catch (Exception ex) {
            LOGGER.error("addressDel " + ex.getMessage());
        }
        return false;
    }

    public static boolean exitConnection() { //function closes connect with DB
        try {
            if (savepoint != null) {
                connAddress.rollback(savepoint);
                connAddress.commit();
                connAddress.close();
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
            connAddress.commit();
            savepoint = connAddress.setSavepoint("savepointMain");
            LOGGER.debug("exitConnection in debug");
        } catch (SQLException e) {
            LOGGER.error("exitConnection " + e.getMessage());
        }

    }

    public static void RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                connAddress.rollback(savepoint);
            LOGGER.debug("RoleBackCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit " + e.getMessage());
        }
    }
}
