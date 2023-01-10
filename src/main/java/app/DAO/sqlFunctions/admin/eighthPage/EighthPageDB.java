package app.DAO.sqlFunctions.admin.eighthPage;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.eighthPage.ExhibitionStatisticsShow;
import org.apache.log4j.Logger;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.EighthPageAdmin.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EighthPageDB { //class used to show statistics of exhibitions
    private static final Logger LOGGER = Logger.getLogger(EighthPageDB.class);

    public static List<ExhibitionStatisticsShow> statisticsShow(String valueRows) { //function shows statisticsExhibition
        try {
            Connection connUserAutorized = HikariConnectDB.getConnection();
            List<ExhibitionStatisticsShow> statisticsList = new ArrayList<>();
            List<String> nameExhibition = new ArrayList<>();
            String ticketCount = null;
            PreparedStatement statementEx = connUserAutorized.prepareStatement(SELECT_NAME_EXHIBITION, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setEx = statementEx.executeQuery();
            Integer countRows=0;
            while (setEx.next()) {
                nameExhibition.add(setEx.getString(1));
            }
            statementEx.close();

            for (int i = 0; i < nameExhibition.size(); i++) {
                PreparedStatement statementTicket = connUserAutorized.prepareStatement(SELECT_COUNT_TICKET + "'" + nameExhibition.get(i) + "';");
                ResultSet setTicket = statementTicket.executeQuery();
                while ((setTicket.next())) {
                    ticketCount = setTicket.getString(1);
                }
                if (valueRows.equals("all"))
                    statisticsList.add(new ExhibitionStatisticsShow(nameExhibition.get(i), ticketCount));
                else {
                    if (countRows < Integer.parseInt(valueRows)) {
                        statisticsList.add(new ExhibitionStatisticsShow(nameExhibition.get(i), ticketCount));
                        countRows++;
                    }
                }
            }
            nameExhibition.clear();
            LOGGER.debug("statisticsShow in debug");
            return statisticsList;
        } catch (Exception e) {
            LOGGER.error("statisticsShow " + e.getMessage());
        }
        return null;
    }

}
