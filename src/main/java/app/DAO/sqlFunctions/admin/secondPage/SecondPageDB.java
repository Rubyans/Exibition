package app.DAO.sqlFunctions.admin.secondPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.secondPage.HallShow;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.SecondPageAdmin.*;

public class SecondPageDB {
    private static final Logger LOGGER = Logger.getLogger(SecondPageDB.class);

    public static List<HallShow> hallShow(String valueRows) { //function show halls data
        try {
            Connection connHall = HikariConnectDB.getConnection();
            List<HallShow> hall = new ArrayList<>();
            String nameHall = null;
            BigDecimal square = null;
            Integer countRows = 0;

            PreparedStatement statement = connHall.prepareStatement(SELECT_HALL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setHall = statement.executeQuery();
            while (setHall.next()) {
                nameHall = setHall.getString(1);
                square = setHall.getBigDecimal(2);
                if (valueRows.equals("all"))
                    hall.add(new HallShow(nameHall, square));
                else {
                    if (countRows < Integer.parseInt(valueRows)) {
                        hall.add(new HallShow(nameHall, square));
                        countRows++;
                    }
                }
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
            Connection connHall = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connHall.setSavepoint("SavepointAdd");
            try {
                PreparedStatement HallAdd = connHall.prepareStatement(INSERT_HALL);
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
            Connection connHall = HikariConnectDB.getConnection();
            Savepoint savepointDel = connHall.setSavepoint("SavepointDel");
            try {
                PreparedStatement ExhibitionDel = connHall.prepareStatement(DELETE_HALL);
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

}
