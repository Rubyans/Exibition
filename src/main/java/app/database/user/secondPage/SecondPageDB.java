package app.database.user.secondPage;

import app.entities.userEntities.secondPage.UserShowExhibition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SecondPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connUserEx;
    private static final Logger LOGGER = LogManager.getLogger(SecondPageDB.class);

    public static void startConnnection() { //function creates connect with DB
        if (connUserEx == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connUserEx = DriverManager.getConnection(url);
                    connUserEx.setAutoCommit(false);
                    savepoint = connUserEx.setSavepoint("savepointMain");
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
        return connUserEx;
    }

    public static void nullConnection() {
        connUserEx = null;
    } //function gives a value of null

    public static List<UserShowExhibition> ShowExhibition(String userId) { //function shows exhibitions data
        try {
            List<UserShowExhibition> user = new ArrayList<>();
            List<String> nameEx = new ArrayList<>();

            String nameExhibition = null;
            String descriptionExibition = null;
            BigDecimal price = null;
            java.sql.Date dateStart = null;
            Date dateEnd = null;

            List<String> expositionName = new ArrayList<>();
            List<String> nameHell = new ArrayList<>();
            List<String> nameAuthor = new ArrayList<>();
            List<String> nameview = new ArrayList<>();
            List<String> addressExibition = new ArrayList<>();


            PreparedStatement statement = connUserEx.prepareStatement("SELECT name FROM exhibitiondb.exhibition " +
                    "INNER JOIN exhibitiondb.ticket ON exhibition.exhibition_id=ticket.ticket_fk " +
                    "INNER JOIN exhibitiondb.authorized_user ON authorized_user.user_id=ticket.user_fk " +
                    "WHERE authorized_user.user_id='" + userId + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setName = statement.executeQuery();
            while (setName.next()) {
                nameEx.add(setName.getString(1));
            }
            statement.close();


            nameEx = nameEx.stream().distinct().collect(Collectors.toList());

            for (String name : nameEx) {
                PreparedStatement resStatment = connUserEx.prepareStatement("Select exhibition.name,description,work_art.name,price,\n" +
                        "date_start,date_end,hall.name,author.first_name,author.last_name,nameview,city,street_or_square,number_home\n" +
                        "FROM exhibitiondb.exhibition INNER JOIN exhibitiondb.exposition ON exhibition.exhibition_id=exposition.exhibition_fk\n" +
                        "INNER JOIN exhibitiondb.work_art ON exposition.art_fk=work_art.art_id\n" +
                        "INNER JOIN exhibitiondb.view_workart ON view_workart.artview_fk=work_art.art_id\n" +
                        "INNER JOIN exhibitiondb.view_art ON view_art.view_id=view_workart.view_fk\n" +
                        "INNER JOIN exhibitiondb.author_workart ON work_art.art_id=author_workart.wart_fk\n" +
                        "INNER JOIN exhibitiondb.author ON author.author_id=author_workart.author_fk\n" +
                        "INNER JOIN exhibitiondb.exhibition_with_address ON exhibition.exhibition_id=exhibition_with_address.ex_fk\n" +
                        "INNER JOIN exhibitiondb.exhibition_address ON exhibition_address.address_id=exhibition_with_address.address_fk\n" +
                        "INNER JOIN exhibitiondb.exhibition_with_hall ON exhibition.exhibition_id=exhibition_with_hall.exhi_fk\n" +
                        "INNER JOIN exhibitiondb.hall ON hall.hall_id=exhibition_with_hall.hall_fk \n" +
                        "where exhibition.name='" + name + "' AND exhibition.access='1';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = resStatment.executeQuery();
                while (resultSet.next()) {
                    nameExhibition = resultSet.getString(1);
                    descriptionExibition = resultSet.getString(2);
                    expositionName.add(resultSet.getString(3));
                    price = resultSet.getBigDecimal(4);
                    dateStart = resultSet.getDate(5);
                    dateEnd = resultSet.getDate(6);
                    nameHell.add(resultSet.getString(7));
                    nameAuthor.add(resultSet.getString(8) + " " + resultSet.getString(9));
                    nameview.add(resultSet.getString(10));
                    addressExibition.add(resultSet.getString(11) + ", " + resultSet.getString(12) + " " + resultSet.getString(13));
                }
                if (nameExhibition != null)
                    user.add(new UserShowExhibition(nameExhibition, descriptionExibition, expositionName, price, dateStart, dateEnd, nameHell, nameAuthor, nameview, addressExibition));
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

    public static boolean exitConnection() { //function closes connect with DB
        try {
            if (savepoint != null) {
                connUserEx.rollback(savepoint);
                connUserEx.commit();
                connUserEx.close();
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

    public static boolean saveCommit() { //function saves data
        try {
            connUserEx.commit();
            savepoint = connUserEx.setSavepoint("savepointMain");
            LOGGER.debug("saveCommit in debug");
            return true;
        } catch (SQLException e) {
            LOGGER.error("saveCommit "+e.getMessage());
            return false;
        }

    }

    public static boolean RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null) {
                connUserEx.rollback(savepoint);
                LOGGER.debug("RoleBackCommit in debug");
                return true;
            }
            LOGGER.debug("RoleBackCommit in debug");
            return false;
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit "+e.getMessage());
            return false;
        }
    }
}
