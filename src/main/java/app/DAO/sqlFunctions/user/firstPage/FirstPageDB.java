package app.DAO.sqlFunctions.user.firstPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.userEntities.firstPage.UserShowAdd;
import app.DAO.entities.userEntities.firstPage.UserShowExhibition;
import app.DAO.entities.userEntities.firstPage.UserShowMoney;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.FirstPageUser.*;

public class FirstPageDB {
    private static final Logger LOGGER = Logger.getLogger(FirstPageDB.class);

    public static List<UserShowExhibition> userShowEx(String userId) { //function shows exhibition data
        try {
            try {
                Connection connUserEx = HikariConnectDB.getConnection();
                List<UserShowExhibition> user = new ArrayList<>();

                String nameExhibition = null;
                Double price = null;
                Date dataStart = null;
                Date dateEnd = null;
                Integer valueTicket = null;

                PreparedStatement statementTicket = connUserEx.prepareStatement(SELECT_NAME_EXHIBITION_INNER + "'" + userId + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                ResultSet setTicket = statementTicket.executeQuery();
                List<String> listNameExhibition = new ArrayList<>();
                HashMap<String, Integer> mapExhibition = new HashMap<String, Integer>();

                while (setTicket.next()) {
                    listNameExhibition.add(setTicket.getString(1));
                }

                for (String nameEx : listNameExhibition) {
                    if (mapExhibition.keySet().contains(nameEx)) {
                        mapExhibition.put(nameEx, mapExhibition.get(nameEx) + 1);
                    } else {
                        mapExhibition.put(nameEx, 1);
                    }
                }
                PreparedStatement statement = connUserEx.prepareStatement(SELECT_EXHIBITION, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setUser = statement.executeQuery();
                while (setUser.next()) {
                    nameExhibition = setUser.getString(1);
                    price = setUser.getDouble(2);
                    dataStart = setUser.getDate(3);
                    dateEnd = setUser.getDate(4);
                    for (String key : mapExhibition.keySet()) {
                        if (key.equals(nameExhibition))
                            valueTicket = mapExhibition.get(key);
                    }
                    user.add(new UserShowExhibition(nameExhibition, price, dataStart, dateEnd, valueTicket));
                    valueTicket = null;
                }
                statement.close();
                statementTicket.close();
                LOGGER.debug("userShowEx in debug");
                return user;
            } catch (Exception e) {
                LOGGER.error("userShowEx " + e.getMessage());
            }
        } catch (Exception ex) {
            LOGGER.error("userShowEx " + ex.getMessage());
        }
        return null;
    }

    public static List<UserShowAdd> showAddExhibition() { //function shows exhibitions data
        try {
            Connection connUserEx = HikariConnectDB.getConnection();
            List<UserShowAdd> userAdd = new ArrayList<>();
            String nameExhibition = null;

            PreparedStatement statement = connUserEx.prepareStatement(SELECT_NAME_EXHIBITION, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setUserAdd = statement.executeQuery();
            while (setUserAdd.next()) {
                nameExhibition = setUserAdd.getString(1);
                userAdd.add(new UserShowAdd(nameExhibition));
            }
            statement.close();
            LOGGER.debug("ShowAddExhibition in debug");
            return userAdd;
        } catch (Exception e) {
            LOGGER.error("ShowAddExhibition " + e.getMessage());
        }
        return null;
    }

    public static List<UserShowMoney> showMoney(String userId) { //function shows money of userAutorized
        try {
            Connection connUserEx = HikariConnectDB.getConnection();
            List<UserShowMoney> userMoney = new ArrayList<>();
            Double amount = null;

            PreparedStatement statement = connUserEx.prepareStatement(SELECT_AMOUNT + "'" + userId + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setUserMoney = statement.executeQuery();
            while (setUserMoney.next()) {
                amount = setUserMoney.getDouble(1);
                userMoney.add(new UserShowMoney(amount));
            }
            statement.close();
            LOGGER.debug("ShowMoney in debug");
            return userMoney;

        } catch (Exception e) {
            LOGGER.error("ShowMoney " + e.getMessage());
        }
        return null;
    }

    public static Boolean ticketAdd(List<String> nameExhibition, String userId) { //function adds tickets data
        try {
            Connection connUserEx = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connUserEx.setSavepoint("SavepointAdd");
            try {

                Double amountUser = 0.0;
                Double amountExhibitions = 0.0;
                Double amountRes = 0.0;
                List<Integer> exhibitionId = new ArrayList<>();

                PreparedStatement statementAmount = connUserEx.prepareStatement(SELECT_AMOUNT + "'" + userId + "';");
                ResultSet resultAmount = statementAmount.executeQuery();
                while (resultAmount.next()) {
                    amountUser = resultAmount.getDouble(1);
                }
                statementAmount.close();

                for (int i = 0; i < nameExhibition.size(); i++) {
                    PreparedStatement statementEx = connUserEx.prepareStatement(SELECT_ID_EXHIBITION + "'" + nameExhibition.get(i) + "';");
                    ResultSet resultEx = statementEx.executeQuery();
                    while (resultEx.next()) {
                        exhibitionId.add(resultEx.getInt(1));
                        amountExhibitions += resultEx.getDouble(2);
                    }
                    statementEx.close();
                }

                amountRes = amountUser - amountExhibitions;

                if (amountRes > 0 || amountRes == 0) {
                    PreparedStatement statementTicket = connUserEx.prepareStatement(INSERT_TICKET);
                    for (int i = 0; i < exhibitionId.size(); i++) {
                        statementTicket.setInt(1, Integer.parseInt(userId));
                        statementTicket.setInt(2, exhibitionId.get(i));
                        statementTicket.execute();
                    }
                    statementTicket.close();
                    PreparedStatement statementUpdate = connUserEx.prepareStatement(UPDATE_USER_AUTHORIZED);
                    statementUpdate.setDouble(1, amountRes);
                    statementUpdate.setInt(2, Integer.parseInt(userId));
                    statementUpdate.execute();
                    statementUpdate.close();
                    LOGGER.debug("ticketAdd in debug");
                    return true;
                } else {
                    LOGGER.debug("ticketAdd in debug");
                    return false;
                }
            } catch (Exception e) {
                LOGGER.error("ticketAdd " + e.getMessage());
                connUserEx.rollback(savepointAdd);
            }
        } catch (Exception ex) {
            LOGGER.error("ticketAdd " + ex.getMessage());
        }
        return false;
    }


}
