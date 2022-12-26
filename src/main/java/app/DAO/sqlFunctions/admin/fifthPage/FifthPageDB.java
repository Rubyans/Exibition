package app.DAO.sqlFunctions.admin.fifthPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.fifthPage.ArtAddShow;
import app.DAO.entities.adminEntities.fifthPage.ArtShow;
import org.apache.log4j.Logger;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.FifthPageAdmin.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FifthPageDB {
    private static final Logger LOGGER = Logger.getLogger(FifthPageDB.class);

    public static List<ArtShow> artShow() { //function shows art
        try {
            Connection connArt = HikariConnectDB.getConnection();
            List<ArtShow> art = new ArrayList<>();
            String name = null;
            Integer yearCreation = 0;
            Double price = null;
            List<String> nameView = new ArrayList<>();
            List<String> fullName = new ArrayList<>();
            List<String> nameArt = new ArrayList<>();

            PreparedStatement statementNameArt = connArt.prepareStatement(SELECT_WORK_ART);
            ResultSet setNameArt = statementNameArt.executeQuery();
            while (setNameArt.next()) {
                nameArt.add(setNameArt.getString(1));
            }

            for (int i = 0; i < nameArt.size(); i++) {
                PreparedStatement statementArt = connArt.prepareStatement(SELECT_FULL_WORK_ART + "'" + nameArt.get(i) + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
        return null;
    }

    public static List<ArtAddShow> addArtShow() { //function shows dates for <select></select>
        try {
            Connection connArt = HikariConnectDB.getConnection();
            List<ArtAddShow> artAddShow = new ArrayList<>();
            List<String> firstName = new ArrayList<>();
            List<String> lastName = new ArrayList<>();
            List<String> email = new ArrayList<>();
            List<String> view = new ArrayList<>();

            PreparedStatement statementAuthor = connArt.prepareStatement(SELECT_AUTHOR, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setAddAuthor = statementAuthor.executeQuery();
            while (setAddAuthor.next()) {
                firstName.add(setAddAuthor.getString(1));
                lastName.add(setAddAuthor.getString(2));
                email.add(setAddAuthor.getString(3));
            }
            statementAuthor.close();

            PreparedStatement statementView = connArt.prepareStatement(SELECT_VIEW_ART, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
        return null;
    }

    public static Boolean artAdd(String name, int yearCreation, Double Price, List<String> author, List<String> view) { //function adds art in DB
        try {
            Connection connArt = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connArt.setSavepoint("SavepointAdd");
            try {
                List<String> viewId = new ArrayList<>();
                List<String> authorId = new ArrayList<>();
                List<String> authorEmail = new ArrayList<>();
                String artId = null;

                PreparedStatement artAdd = connArt.prepareStatement(INSERT_WORK_ART);
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
                    PreparedStatement showAuthorId = connArt.prepareStatement(SELECT_ID_AUTHOR + "'" + authorEmail.get(i) + "'");
                    ResultSet setShowAuthor = showAuthorId.executeQuery();
                    while (setShowAuthor.next()) {
                        authorId.add(setShowAuthor.getString(1));

                    }
                    setShowAuthor.close();
                }

                for (int i = 0; i < view.size(); i++) {
                    PreparedStatement showViewId = connArt.prepareStatement(SELECT_ID_VIEW_ART + "'" + view.get(i) + "'");
                    ResultSet setShowView = showViewId.executeQuery();
                    while (setShowView.next()) {
                        viewId.add(setShowView.getString(1));
                    }
                    showViewId.close();
                }

                PreparedStatement showArtId = connArt.prepareStatement(SELECT_ID_WORK_ART + "'" + name + "'");
                ResultSet setShowArtId = showArtId.executeQuery();
                while (setShowArtId.next()) {
                    artId = setShowArtId.getString(1);
                }

                for (int i = 0; i < authorId.size(); i++) {
                    PreparedStatement statementAddAuthor = connArt.prepareStatement(INSERT_AUTHOR_WORKART);
                    statementAddAuthor.setInt(1, Integer.parseInt(authorId.get(i)));
                    statementAddAuthor.setInt(2, Integer.parseInt(artId));
                    statementAddAuthor.execute();
                    statementAddAuthor.close();
                }

                for (int i = 0; i < viewId.size(); i++) {
                    PreparedStatement statementAddAuthor = connArt.prepareStatement(INSERT_VIEW_WORKART);
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
            Connection connArt = HikariConnectDB.getConnection();
            Savepoint savepointDel = connArt.setSavepoint("SavepointDel");
            try {
                PreparedStatement artDel = connArt.prepareStatement(DELETE_WORK_ART);
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

}
