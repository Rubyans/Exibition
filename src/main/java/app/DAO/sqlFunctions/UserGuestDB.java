package app.DAO.sqlFunctions;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.UserGuest;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.UserGuest.SELECT_EXHIBITION;

public class UserGuestDB {

    private static final Logger LOGGER = Logger.getLogger(UserGuestDB.class);


    public static List<UserGuest> authorizationUser() { //function shows exhibitions data
        try {
            Connection connGuest = HikariConnectDB.getConnection();
            PreparedStatement statement = connGuest.prepareStatement(SELECT_EXHIBITION, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery();
            Integer rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            String nameExibition = null;
            String descriptionExibition = null;
            List<String> expositionName = new ArrayList<>();
            Double price = null;
            Date dateStart = null;
            Date dateEnd = null;

            List<UserGuest> guest = new ArrayList<>();

            resultSet.first();
            String temp = resultSet.getString(1);
            resultSet.beforeFirst();
            Integer count = 0;
            for (int i = 0; i < rowCount; i++) {
                resultSet.next();

                if (temp.equals(resultSet.getString(1)) == false) {
                    nameExibition = resultSet.getString(1);
                    descriptionExibition = resultSet.getString(2);
                    price = resultSet.getDouble(4);
                    dateStart = resultSet.getDate(5);
                    dateEnd = resultSet.getDate(6);
                    temp = resultSet.getString(1);
                    resultSet.beforeFirst();
                    while (resultSet.next()) {
                        if (temp.equals(resultSet.getString(1)) == true) {
                            expositionName.add(resultSet.getString(3));
                        }
                    }
                    resultSet.absolute(i + 1);
                    guest.add(new UserGuest(nameExibition, descriptionExibition, expositionName, price, dateStart, dateEnd));
                    expositionName.clear();

                } else {
                    if (count == 0) {
                        nameExibition = resultSet.getString(1);
                        descriptionExibition = resultSet.getString(2);
                        price = resultSet.getDouble(4);
                        dateStart = resultSet.getDate(5);
                        dateEnd = resultSet.getDate(6);
                        resultSet.beforeFirst();
                        while (resultSet.next()) {
                            if (temp.equals(resultSet.getString(1)) == true) {
                                expositionName.add(resultSet.getString(3));
                            }
                        }
                        guest.add(new UserGuest(nameExibition, descriptionExibition, expositionName, price, dateStart, dateEnd));
                        resultSet.absolute(i + 1);
                        count++;
                        expositionName.clear();
                    }
                }
            }
            statement.close();
            LOGGER.debug("authorizationUser in debug");
            return guest;
        } catch (Exception ex) {
            LOGGER.error("authorizationUser " + ex.getMessage());
        }
        return null;
    }

}
