package app.DAO.sqlFunctions.admin.thirdPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.thirdPage.AddressShow;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.ThirdPageAdmin.*;

public class ThirdPageDB {
    private static final Logger LOGGER = Logger.getLogger(ThirdPageDB.class);

    public static List<AddressShow> addressShow() { //function shows address
        try {
            Connection connAddress = HikariConnectDB.getConnection();
            List<AddressShow> address = new ArrayList<>();

            Integer Unumber;
            String country = null;
            String city = null;
            String street = null;
            Integer numberHouse;

            PreparedStatement statement = connAddress.prepareStatement(SELECT_ADDRESS, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
            Connection connAddress = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connAddress.setSavepoint("SavepointAdd");
            try {
                PreparedStatement AddressAdd = connAddress.prepareStatement(INSERT_ADDRESS);
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
            Connection connAddress = HikariConnectDB.getConnection();
            Savepoint savepointDel = connAddress.setSavepoint("SavepointDel");
            try {
                PreparedStatement AddressDel = connAddress.prepareStatement(DELETE_ADDRESS);
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
}
