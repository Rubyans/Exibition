package app.database.admin.fifthPage;

import app.entities.adminEntities.fifthPage.ArtAddShow;
import app.entities.adminEntities.fifthPage.ArtShow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FifthPageDB {
    private static final Logger LOGGER = LogManager.getLogger(FifthPageDB.class);
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connArt;

    public static void startConnnection() { //create database connections
        if (connArt == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connArt = DriverManager.getConnection(url);
                    connArt.setAutoCommit(false);
                    savepoint = connArt.setSavepoint("savepointMain"); //create savepoint for transactions
                    LOGGER.debug("startConnection in debug");
                } catch (SQLException e) {
                    LOGGER.error("startConnection " + e.getMessage());
                }
            } catch (Exception e) {
                LOGGER.error("startConnection " + e.getMessage());
            }
        }
    }
    public static Connection checkConnection() {
        return connArt;
    } //checking database connections

    public static void nullConnection() {
        connArt = null;
    } //to do connection=null

    public static List<ArtShow> artShow() { //function shows art
        try {
            try {
                List<ArtShow> art = new ArrayList<>();
                String name = null;
                Integer yearCreation = 0;
                Double price = null;
                List<String> nameView = new ArrayList<>();
                List<String> fullName = new ArrayList<>();
                List<String> nameArt = new ArrayList<>();

                PreparedStatement statementNameArt = connArt.prepareStatement("SELECT name FROM exhibitiondb.work_art");
                ResultSet setNameArt = statementNameArt.executeQuery();
                while (setNameArt.next()) {
                    nameArt.add(setNameArt.getString(1));
                }

                for (int i = 0; i < nameArt.size(); i++) {
                    PreparedStatement statementArt = connArt.prepareStatement("SELECT name,creation_year,appraised_value,nameview,first_name,last_name,email\n" +
                            "FROM exhibitiondb.work_art \n" +
                            "INNER JOIN exhibitiondb.author_workart ON work_art.art_id=author_workart.wart_fk\n" +
                            "INNER JOIN exhibitiondb.author ON author_workart.author_fk=author.author_id\n" +
                            "INNER JOIN exhibitiondb.view_workart ON work_art.art_id=view_workart.artview_fk\n" +
                            "INNER JOIN exhibitiondb.view_art ON view_workart.view_fk=view_art.view_id " +
                            "WHERE name='" + nameArt.get(i) + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet setArt = statementArt.executeQuery();
                    while (setArt.next()) {
                        name = setArt.getString(1);
                        yearCreation = setArt.getInt(2);
                        price = setArt.getDouble(3);
                        nameView.add(setArt.getString(4));
                        fullName.add(setArt.getString(5) + " " + setArt.getString(6) + " " + setArt.getString(7));
                    }
                    if (name != null)
                        art.add(new ArtShow(name, yearCreation, price, nameView, fullName));
                    name = null;
                    yearCreation = 0;
                    price = null;
                    nameView.clear();
                    fullName.clear();
                    statementArt.close();
                }
                LOGGER.debug("artShow in debug");
                return art;
            } catch (Exception e) {
                LOGGER.error("artShow " + e.getMessage());
            }
        } catch (Exception ex) {
            LOGGER.error("artShow " + ex.getMessage());
        }
        return null;
    }

    public static List<ArtAddShow> addArtShow() { //function shows dates for <select></select>
        try {
            try {
                List<ArtAddShow> artAddShow = new ArrayList<>();
                List<String> firstName = new ArrayList<>();
                List<String> lastName = new ArrayList<>();
                List<String> email = new ArrayList<>();
                List<String> view = new ArrayList<>();

                PreparedStatement statementAuthor = connArt.prepareStatement("SELECT first_name,last_name,email FROM exhibitiondb.author", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setAddAuthor = statementAuthor.executeQuery();
                while (setAddAuthor.next()) {
                    firstName.add(setAddAuthor.getString(1));
                    lastName.add(setAddAuthor.getString(2));
                    email.add(setAddAuthor.getString(3));
                }
                statementAuthor.close();

                PreparedStatement statementView = connArt.prepareStatement("SELECT nameview FROM exhibitiondb.view_art", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setAddView = statementView.executeQuery();
                while (setAddView.next()) {
                    view.add(setAddView.getString(1));

                }
                statementView.close();

                artAddShow.add(new ArtAddShow(firstName, lastName, email, view));
                LOGGER.debug("addArtShow in debug");
                return artAddShow;
            } catch (Exception e) {
                LOGGER.error("addArtShow " + e.getMessage());
            }
        } catch (Exception ex) {
            LOGGER.error("addArtShow " + ex.getMessage());
        }
        return null;
    }

    public static Boolean artAdd(String name, int yearCreation, Double Price, List<String> author, List<String> view) { //function adds art in DB
        try {
            Savepoint savepointAdd = connArt.setSavepoint("SavepointAdd");
            try {
                List<String> viewId = new ArrayList<>();
                List<String> authorId = new ArrayList<>();
                List<String> authorEmail = new ArrayList<>();
                String artId = null;

                PreparedStatement artAdd = connArt.prepareStatement("INSERT into exhibitiondb.work_art (name,creation_year,appraised_value) values (?,?,?)");
                artAdd.setString(1, name);
                artAdd.setInt(2, yearCreation);
                artAdd.setDouble(3, Price);
                artAdd.execute();
                artAdd.close();

                for (int i = 0; i < author.size(); i++) {
                    String[] email = author.get(i).split(" ");
                    authorEmail.add(email[2]);
                }

                for (int i = 0; i < authorEmail.size(); i++) {
                    PreparedStatement showAuthorId = connArt.prepareStatement("SELECT author_id FROM exhibitiondb.author WHERE email='" + authorEmail.get(i) + "'");
                    ResultSet setShowAuthor = showAuthorId.executeQuery();
                    while (setShowAuthor.next()) {
                        authorId.add(setShowAuthor.getString(1));

                    }
                    setShowAuthor.close();
                }

                for (int i = 0; i < view.size(); i++) {
                    PreparedStatement showViewId = connArt.prepareStatement("SELECT view_id FROM exhibitiondb.view_art WHERE nameview='" + view.get(i) + "'");
                    ResultSet setShowView = showViewId.executeQuery();
                    while (setShowView.next()) {
                        viewId.add(setShowView.getString(1));
                    }
                    showViewId.close();
                }

                PreparedStatement showArtId = connArt.prepareStatement("SELECT art_id FROM exhibitiondb.work_art WHERE name='" + name + "'");
                ResultSet setShowArtId = showArtId.executeQuery();
                while (setShowArtId.next()) {
                    artId = setShowArtId.getString(1);
                }

                for (int i = 0; i < authorId.size(); i++) {
                    PreparedStatement statementAddAuthor = connArt.prepareStatement("INSERT into exhibitiondb.author_workart (author_fk,wart_fk) values (?,?)");
                    statementAddAuthor.setInt(1, Integer.parseInt(authorId.get(i)));
                    statementAddAuthor.setInt(2, Integer.parseInt(artId));
                    statementAddAuthor.execute();
                    statementAddAuthor.close();
                }

                for (int i = 0; i < viewId.size(); i++) {
                    PreparedStatement statementAddAuthor = connArt.prepareStatement("INSERT into exhibitiondb.view_workart (artview_fk,view_fk) values (?,?)");
                    statementAddAuthor.setInt(1, Integer.parseInt(artId));
                    statementAddAuthor.setInt(2, Integer.parseInt(viewId.get(i)));
                    statementAddAuthor.execute();
                    statementAddAuthor.close();
                }
                LOGGER.debug("artAdd in debug");
                return true;
            } catch (Exception e) {
                LOGGER.error("artAdd " + e.getMessage());
                connArt.rollback(savepointAdd);
            }
        } catch (Exception e) {
            LOGGER.error("artAdd " + e.getMessage());
        }
        return false;
    }

    public static Boolean artDel(String email) { //function deletes art
        try {
            Savepoint savepointDel = connArt.setSavepoint("SavepointDel");
            try {
                PreparedStatement artDel = connArt.prepareStatement("DELETE FROM exhibitiondb.work_art WHERE name=?");
                artDel.setString(1, email);
                Integer row = artDel.executeUpdate();
                artDel.close();
                if (row > 0) {
                    LOGGER.debug("artDel in debug");
                    return true;
                }
                LOGGER.debug("artDel in debug");
                return false;
            } catch (Exception e) {
                LOGGER.error("artDel in debug");
                connArt.rollback(savepointDel);
            }
        } catch (Exception e) {
            LOGGER.error("artDel in debug");
        }
        return false;
    }

    public static boolean exitConnection() { //function exits(close) connection
        try {
            if (savepoint != null) {
                connArt.rollback(savepoint);
                connArt.commit();
                connArt.close();
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

    public static void saveCommit() { //function saves dates (transacion)
        try {
            connArt.commit();
            savepoint = connArt.setSavepoint("savepointMain");
            LOGGER.debug("saveCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("saveCommit " + e.getMessage());
        }

    }

    public static void RoleBackCommit() { //function roleback dates (transacion)
        try {
            if (savepoint != null)
                connArt.rollback(savepoint);
            LOGGER.debug("RoleBackCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit " + e.getMessage());
        }

    }
}
