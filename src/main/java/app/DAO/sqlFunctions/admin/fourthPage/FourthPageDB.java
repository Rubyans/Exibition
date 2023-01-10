package app.DAO.sqlFunctions.admin.fourthPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.fourthPage.AuthorShow;
import org.apache.log4j.Logger;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.FourthPageAdmin.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FourthPageDB {
    private static final Logger LOGGER = Logger.getLogger(FourthPageDB.class);

    public static List<AuthorShow> authorShow(String valueRows) { //function shows author
        try {
            Connection connAuthor = HikariConnectDB.getConnection();
            List<AuthorShow> author = new ArrayList<>();
            String firstName = null;
            String lastName = null;
            String email = null;
            Integer countRows = 0;

            PreparedStatement statement = connAuthor.prepareStatement(SELECT_AUTHOR, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setAuthor = statement.executeQuery();
            while (setAuthor.next()) {
                firstName = setAuthor.getString(1);
                lastName = setAuthor.getString(2);
                email = setAuthor.getString(3);
                if (valueRows.equals("all"))
                    author.add(new AuthorShow(firstName, lastName, email));
                else {
                    if (countRows < Integer.parseInt(valueRows)) {
                        author.add(new AuthorShow(firstName, lastName, email));
                        countRows++;
                    }
                }
            }
            statement.close();
            LOGGER.debug("authorShow in debug");
            return author;
        } catch (Exception e) {
            LOGGER.error("authorShow " + e.getMessage());
        }
        return null;
    }

    public static Boolean authorAdd(String firstName, String lastName, String email) { //function adds author data
        try {
            Connection connAuthor = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connAuthor.setSavepoint("SavepointAdd");
            try {
                PreparedStatement AuthorAdd = connAuthor.prepareStatement(INSERT_AUTHOR);
                AuthorAdd.setString(1, firstName);
                AuthorAdd.setString(2, lastName);
                AuthorAdd.setString(3, email);
                AuthorAdd.execute();
                AuthorAdd.close();
                LOGGER.debug("authorAdd in debug");
                return true;
            } catch (Exception e) {
                LOGGER.error("authorAdd " + e.getMessage());
                connAuthor.rollback(savepointAdd);
            }
        } catch (Exception ex) {
            LOGGER.error("authorAdd " + ex.getMessage());
        }
        return false;
    }

    public static Boolean authorDel(String email) { //function deletes authors data
        try {
            Connection connAuthor = HikariConnectDB.getConnection();
            Savepoint savepointDel = connAuthor.setSavepoint("SavepointDel");
            try {

                PreparedStatement AuthorDel = connAuthor.prepareStatement(DELETE_AUTHOR);
                AuthorDel.setString(1, email);
                int row = AuthorDel.executeUpdate();
                AuthorDel.close();

                if (row > 0) {
                    LOGGER.debug("authorDel in debug");
                    return true;
                }
                LOGGER.debug("authorDel in debug");
                return false;
            } catch (Exception e) {
                LOGGER.error("authorDel " + e.getMessage());
                connAuthor.rollback(savepointDel);
            }
        } catch (Exception ex) {
            LOGGER.error("authorDel " + ex.getMessage());
        }
        return false;
    }

}
