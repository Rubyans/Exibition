package app.database.admin.eighthPage;


import app.entities.adminEntities.eighthPage.ExhibitionStatisticsShow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EighthPageDB { //class used to show statistics of exhibitions
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connUserAutorized;
    private static final Logger LOGGER = LogManager.getLogger(EighthPageDB.class);

    public static void startConnnection() { //function creates connect with DB
        if (connUserAutorized == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connUserAutorized = DriverManager.getConnection(url);
                    connUserAutorized.setAutoCommit(false);
                    savepoint = connUserAutorized.setSavepoint("savepointMain");
                    LOGGER.debug("startConnection in debug");
                } catch (SQLException e) {
                    LOGGER.error("startConnnection " + e.getMessage());
                }
            } catch (Exception ex) {
                LOGGER.error("startConnnection " + ex.getMessage());
            }
        }
    }

    public static Connection checkConnection() {
        return connUserAutorized;
    }

    public static void nullConnection() {
        connUserAutorized = null;
    }

    public static List<ExhibitionStatisticsShow> statisticsShow() { //function shows statisticsExhibition
        try {
            try {
                List<ExhibitionStatisticsShow> statisticsList = new ArrayList<>();
                List<String> nameExhibition = new ArrayList<>();
                String ticketCount = null;
                PreparedStatement statementEx = connUserAutorized.prepareStatement("SELECT name FROM exhibitiondb.exhibition", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setEx = statementEx.executeQuery();
                while (setEx.next()) {
                    nameExhibition.add(setEx.getString(1));
                }
                statementEx.close();

                for (int i = 0; i < nameExhibition.size(); i++) {
                    PreparedStatement statementTicket = connUserAutorized.prepareStatement("SELECT COUNT(user_fk)\n" +
                            "FROM exhibitiondb.ticket INNER JOIN exhibitiondb.exhibition ON ticket.ticket_fk=exhibition.exhibition_id\n" +
                            "WHERE exhibition.name='" + nameExhibition.get(i) + "';");
                    ResultSet setTicket = statementTicket.executeQuery();
                    while ((setTicket.next())) {
                        ticketCount=setTicket.getString(1);
                    }
                    statisticsList.add(new ExhibitionStatisticsShow(nameExhibition.get(i),ticketCount));
                }
                nameExhibition.clear();
                LOGGER.debug("statisticsShow in debug");
                return statisticsList;
            } catch (Exception e) {
                LOGGER.error("statisticsShow " + e.getMessage());
            }
        } catch (Exception ex) {
            LOGGER.error("statisticsShow " + ex.getMessage());
        }
        return null;
    }


    public static boolean exitConnection() { //function closes connect
        try {
            if (savepoint != null) {
                connUserAutorized.rollback(savepoint);
                connUserAutorized.commit();
                connUserAutorized.close();
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
            connUserAutorized.commit();
            savepoint = connUserAutorized.setSavepoint("savepointMain");
            LOGGER.debug("saveCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("saveCommit " + e.getMessage());
        }
    }

    public static void RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                connUserAutorized.rollback(savepoint);
            LOGGER.debug("RoleBackCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit " + e.getMessage());
        }
    }
}
