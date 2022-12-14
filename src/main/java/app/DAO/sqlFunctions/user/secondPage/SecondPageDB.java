package app.DAO.sqlFunctions.user.secondPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.userEntities.secondPage.UserShowExhibition;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.SecondPageUser.*;

public class SecondPageDB {
    private static final Logger LOGGER = Logger.getLogger(SecondPageDB.class);

    public static List<UserShowExhibition> showExhibition(String valueRows, String userId) { //function shows exhibitions data
        try {
            Connection connUserEx = HikariConnectDB.getConnection();
            List<UserShowExhibition> user = new ArrayList<>();
            List<String> nameEx = new ArrayList<>();

            Integer countRows = 0;

            String nameExhibition = null;
            String descriptionExibition = null;
            String hours = null;
            BigDecimal price = null;
            java.sql.Date dateStart = null;
            Date dateEnd = null;

            List<String> expositionName = new ArrayList<>();
            List<String> nameHell = new ArrayList<>();
            List<String> nameAuthor = new ArrayList<>();
            List<String> nameview = new ArrayList<>();
            List<String> addressExibition = new ArrayList<>();


            PreparedStatement statement = connUserEx.prepareStatement(SELECT_NAME_EXHIBITION + "'" + userId + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setName = statement.executeQuery();
            while (setName.next()) {
                nameEx.add(setName.getString(1));
            }
            statement.close();


            nameEx = nameEx.stream().distinct().collect(Collectors.toList());

            for (String name : nameEx) {
                PreparedStatement resStatment = connUserEx.prepareStatement(SELECT_EXHIBITION + "'" + name + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = resStatment.executeQuery();
                while (resultSet.next()) {
                    nameExhibition = resultSet.getString(1);
                    descriptionExibition = resultSet.getString(2);
                    expositionName.add(resultSet.getString(3));
                    price = resultSet.getBigDecimal(4);
                    dateStart = resultSet.getDate(5);
                    dateEnd = resultSet.getDate(6);
                    hours = resultSet.getString(7);
                    nameHell.add(resultSet.getString(8));
                    nameAuthor.add(resultSet.getString(9) + " " + resultSet.getString(10));
                    nameview.add(resultSet.getString(11));
                    addressExibition.add(resultSet.getString(12) + ", " + resultSet.getString(13) + " " + resultSet.getString(14));
                }
                if (nameExhibition != null) {
                    if (valueRows.equals("all"))
                        user.add(new UserShowExhibition(nameExhibition, descriptionExibition, expositionName, price, dateStart, dateEnd, hours, nameHell, nameAuthor, nameview, addressExibition));
                    else {
                        if (countRows < Integer.parseInt(valueRows)) {
                            user.add(new UserShowExhibition(nameExhibition, descriptionExibition, expositionName, price, dateStart, dateEnd, hours, nameHell, nameAuthor, nameview, addressExibition));
                            countRows++;
                        }
                    }
                }
                nameExhibition = null;
                descriptionExibition = null;
                price = null;
                dateStart = null;
                dateEnd = null;
                expositionName.clear();
                nameHell.clear();
                nameAuthor.clear();
                nameview.clear();
                addressExibition.clear();
            }
            LOGGER.debug("ShowExhibition in debug");
            return user;
        } catch (Exception e) {
            LOGGER.error("ShowExhibition " + e.getMessage());
        }
        return null;
    }

}
