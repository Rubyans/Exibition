package app.DAO.sqlFunctions.admin.sixthPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.sixthPage.ViewShow;
import org.apache.log4j.Logger;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.SixthPageAdmin.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SixthPageDB {
    private static final Logger LOGGER = Logger.getLogger(SixthPageDB.class);

    public static List<ViewShow> viewShow(String valueRows) { //function shows views data
        try {
            Connection connView = HikariConnectDB.getConnection();
            List<ViewShow> view = new ArrayList<>();
            String name;
            Integer viewId;
            Integer countRows = 0;
            PreparedStatement statement = connView.prepareStatement(SELECT_VIEW_ART, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setView = statement.executeQuery();
            while (setView.next()) {
                viewId = setView.getInt(1);
                name = setView.getString(2);
                if (valueRows.equals("all"))
                    view.add(new ViewShow(viewId, name));
                else {
                    if (countRows < Integer.parseInt(valueRows)) {
                        view.add(new ViewShow(viewId, name));
                        countRows++;
                    }
                }
            }
            statement.close();
            LOGGER.debug("viewShow in debug");
            return view;
        } catch (Exception e) {
            LOGGER.error("viewShow " + e.getMessage());
        }
        return null;
    }

    public static Boolean viewAdd(String name) { //function adds views data
        try {
            Connection connView = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connView.setSavepoint("SavepointAdd");
            try {
                PreparedStatement viewAdd = connView.prepareStatement(INSERT_VIEW_ART);
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
            Connection connView = HikariConnectDB.getConnection();
            Savepoint savepointDel = connView.setSavepoint("SavepointDel");
            try {
                PreparedStatement viewDel = connView.prepareStatement(DELETE_VIEW_ART);
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
}
