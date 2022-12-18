package app.database;


import app.entities.UserGuest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserGuestDB {

    private static final Logger LOGGER = LogManager.getLogger(UserGuestDB.class);
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Connection connGuest;
    private static Savepoint savepoint;

    public static void startConnnection() { //function creates connect with DB
        if (connGuest == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connGuest = DriverManager.getConnection(url);
                    connGuest.setAutoCommit(false);
                    savepoint = connGuest.setSavepoint("savepointMain");
                    LOGGER.debug("startConnnection in debug");
                } catch (SQLException e) {
                    LOGGER.error("startConnnection " + e.getMessage());
                }
            } catch (Exception e) {
                LOGGER.error("startConnnection " + e.getMessage());
            }
        }
    }

    public static Connection checkConnection() {
        return connGuest;
    }

    public static void nullConnection() {
        connGuest = null;
    } //function gives a value of null

    public static boolean exitConnection() { //function closes connect with DB
        try {
            if (savepoint != null) {
                connGuest.close();
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

    public static List<UserGuest> authorizationUser() { //function shows exhibitions data
        try {
            PreparedStatement statement = connGuest.prepareStatement("SELECT exhibition.name,description,work_art.name,price,date_start,date_end " +
                            "FROM exhibitiondb.exhibition INNER JOIN exhibitiondb.exposition ON exhibition.exhibition_id=exposition.exhibition_fk " +
                            "INNER JOIN exhibitiondb.work_art ON exposition.art_fk=work_art.art_id WHERE exhibition.access='1';", ResultSet.TYPE_SCROLL_SENSITIVE,
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
            BigDecimal price = null;
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
                    price = resultSet.getBigDecimal(4);
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
                        price = resultSet.getBigDecimal(4);
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
            LOGGER.debug("exitConnection in debug");
            return guest;
        } catch (Exception ex) {
            LOGGER.error("exitConnection " + ex.getMessage());
        }
        return null;
    }
}
