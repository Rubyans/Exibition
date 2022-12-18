package app.database.admin.fourthPage;

import app.entities.adminEntities.fourthPage.AuthorShow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FourthPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connAuthor;

    private static final Logger LOGGER = LogManager.getLogger(FourthPageDB.class);

    public static void startConnnection() //function creates connect with DB
    {
        if (connAuthor == null) {
            try {
                try {
                    connAuthor = DriverManager.getConnection(url);
                    connAuthor.setAutoCommit(false);
                    savepoint = connAuthor.setSavepoint("savepointMain");
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
        return connAuthor;
    }

    public static void nullConnection() {
        connAuthor = null;
    }

    public static List<AuthorShow> authorShow() { //function shows author
        try {
            try {
                List<AuthorShow> author = new ArrayList<>();
                String firstName = null;
                String lastName = null;
                String email = null;

                PreparedStatement statement = connAuthor.prepareStatement("SELECT first_name,last_name,email FROM exhibitiondb.author", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setAuthor = statement.executeQuery();
                while (setAuthor.next()) {
                    firstName = setAuthor.getString(1);
                    lastName = setAuthor.getString(2);
                    email = setAuthor.getString(3);

                    author.add(new AuthorShow(firstName, lastName, email));
                }
                statement.close();
                LOGGER.debug("authorShow in debug");
                return author;
            } catch (Exception e) {
                LOGGER.error("authorShow " + e.getMessage());
            }
        } catch (Exception ex) {
            LOGGER.error("authorShow " + ex.getMessage());
        }
        return null;
    }

    public static Boolean authorAdd(String firstName, String lastName, String email) { //function adds author data
        try {
            Savepoint savepointAdd = connAuthor.setSavepoint("SavepointAdd");
            try {
                PreparedStatement AuthorAdd = connAuthor.prepareStatement("INSERT into exhibitiondb.author (first_name,last_name,email) values (?,?,?)");
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
            Savepoint savepointDel = connAuthor.setSavepoint("SavepointDel");
            try {

                PreparedStatement AuthorDel = connAuthor.prepareStatement("DELETE FROM exhibitiondb.author WHERE email=?");
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

    public static boolean exitConnection() { //function closes commit
        try {
            if (savepoint != null) {
                connAuthor.rollback(savepoint);
                connAuthor.commit();
                connAuthor.close();
                LOGGER.debug("exitConnection in debug");
                return true;
            }
            LOGGER.debug("exitConnection in debug");
            return false;
        } catch (SQLException e) {
            LOGGER.error("exitConnection "+e.getMessage());
            return false;
        }
    }
    public static void saveCommit() { //function saves commit
        try {
            connAuthor.commit();
            savepoint = connAuthor.setSavepoint("savepointMain");
            LOGGER.debug("saveCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("saveCommit "+e.getMessage());
        }
    }

    public static void RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                connAuthor.rollback(savepoint);
            LOGGER.debug("RoleBackCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit "+e.getMessage());
        }

    }
}
