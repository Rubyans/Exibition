package app.DAO.sqlFunctions.admin.firstPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.firstPage.AdminAddShow;
import app.DAO.entities.adminEntities.firstPage.AdminShow;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.DAO.sqlFunctions.sqlRequests.SQLRequests.FirstPageAdmin.*;

public class FirstPageDB {
    private static final Logger LOGGER = Logger.getLogger(FirstPageDB.class);

    public static List<AdminShow> exhibitionShow() { //function shows(SELECT) exhibitions data
        try {
            Connection connExhibition = HikariConnectDB.getConnection();

            List<AdminShow> admin = new ArrayList<>();
            List<String> nameEx = new ArrayList<>();

            String nameExhibition = null;
            String descriptionExibition = null;
            String access = null;

            Double price = null;
            Date dateStart = null;
            Date dateEnd = null;

            List<String> expositionName = new ArrayList<>();
            List<String> nameHell = new ArrayList<>();
            List<String> nameAuthor = new ArrayList<>();
            List<String> nameview = new ArrayList<>();
            List<String> addressExibition = new ArrayList<>();


            PreparedStatement statement = connExhibition.prepareStatement(SELECT_NAME_EXHIBITION, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setName = statement.executeQuery();
            while (setName.next()) {
                nameEx.add(setName.getString(1));
            }
            statement.close();

            for (String name : nameEx) {
                PreparedStatement resStatment = connExhibition.prepareStatement(SELECT_EXHIBITION + "'" + name + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = resStatment.executeQuery();
                while (resultSet.next()) {
                    nameExhibition = resultSet.getString(1);
                    descriptionExibition = resultSet.getString(2);
                    expositionName.add(resultSet.getString(3));
                    price = resultSet.getDouble(4);
                    dateStart = resultSet.getDate(5);
                    dateEnd = resultSet.getDate(6);
                    access = resultSet.getString(7);
                    nameHell.add(resultSet.getString(8));
                    nameAuthor.add(resultSet.getString(9) + " " + resultSet.getString(10));
                    nameview.add(resultSet.getString(11));
                    addressExibition.add(resultSet.getString(12) + ", " + resultSet.getString(13) + " " + resultSet.getString(14));
                }
                if (nameExhibition != null)
                    admin.add(new AdminShow(nameExhibition, descriptionExibition, expositionName, price, dateStart, dateEnd, access, nameHell, nameAuthor, nameview, addressExibition));
                nameExhibition = null;
                descriptionExibition = null;
                access = null;
                price = null;
                dateStart = null;
                dateEnd = null;
                expositionName.clear();
                nameHell.clear();
                nameAuthor.clear();
                nameview.clear();
                addressExibition.clear();
                resStatment.close();
            }
            LOGGER.debug("exibitionShow in debug");
            return admin;
        } catch (Exception e) {
            LOGGER.error("exibitionShow " + e.getMessage());
        }
        return null;
    }

    public static List<AdminAddShow> showAddFirstPage() { //function shows information for <select></select>
        try {
            Connection connExhibition = HikariConnectDB.getConnection();
            List<AdminAddShow> AdminAddFirstPage = new ArrayList<>();

            List<String> hall = new ArrayList<>();
            List<String> address = new ArrayList<>();
            List<String> art = new ArrayList<>();

            PreparedStatement statement = connExhibition.prepareStatement(SELECT_NAME_HALL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setHall = statement.executeQuery();
            while (setHall.next()) {
                hall.add(setHall.getString(1));
            }

            statement = connExhibition.prepareStatement(SELECT_ADDRESS, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setAddress = statement.executeQuery();
            while (setAddress.next()) {
                address.add(setAddress.getString(1) + " " + setAddress.getString(2) + " " + setAddress.getString(3) + " " + setAddress.getString(4));
            }

            statement = connExhibition.prepareStatement(SELECT_WORK_ART, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setArt = statement.executeQuery();
            while (setArt.next()) {
                art.add(setArt.getString(1));
            }

            AdminAddFirstPage.add(new AdminAddShow(hall, address, art));

            statement.close();
            LOGGER.debug("ShowAddFirstPage in debug");
            return AdminAddFirstPage;
        } catch (Exception e) {
            LOGGER.error("ShowAddFirstPage " + e.getMessage());
        }
        return null;
    }

    public static Boolean addFirstPage(String nameExibition, String description, Double price, String start, String end, List<String> hall, List<String> address, List<String> workArt) { //function adds exhibitions-data
        try {
            Connection connExhibition = HikariConnectDB.getConnection();
            Savepoint savepointAdd = connExhibition.setSavepoint("SavepointAdd");
            try {
                Integer ExhibitionPK = 0;
                List<Integer> HellPK = new ArrayList<>();
                List<Integer> AddressPK = new ArrayList<>();
                List<Integer> WorkArtPK = new ArrayList<>();

                PreparedStatement ExhibitionADD = connExhibition.prepareStatement(INSERT_EXHIBITION);
                ExhibitionADD.setString(1, nameExibition);
                ExhibitionADD.setString(2, description);
                ExhibitionADD.setDouble(3, price);
                ExhibitionADD.setString(4, start);
                ExhibitionADD.setString(5, end);
                ExhibitionADD.setInt(6, 1);
                ExhibitionADD.execute();
                ExhibitionADD.close();

                PreparedStatement PreparedExhPK = connExhibition.prepareStatement(SELECT_ID_EXHIBITION);
                PreparedExhPK.setString(1, nameExibition);
                ResultSet ExhibitionPKSet = PreparedExhPK.executeQuery();
                while (ExhibitionPKSet.next()) {
                    ExhibitionPK = ExhibitionPKSet.getInt(1);
                }
                PreparedExhPK.close();


                PreparedStatement PreparedHallPK = connExhibition.prepareStatement(SELECT_ID_HALL);
                for (int i = 0; i < hall.size(); i++) {
                    PreparedHallPK.setString(1, hall.get(i));
                    ResultSet SetHallPK = PreparedHallPK.executeQuery();
                    while (SetHallPK.next()) {
                        HellPK.add(SetHallPK.getInt(1));
                    }
                }
                PreparedHallPK.close();

                PreparedStatement PreparedHallwithExh = connExhibition.prepareStatement(INSERT_EXHIBITION_WITH_HALL);
                for (int i = 0; i < HellPK.size(); i++) {
                    PreparedHallwithExh.setInt(1, HellPK.get(i));
                    PreparedHallwithExh.setInt(2, ExhibitionPK);
                    PreparedHallwithExh.execute();
                }
                PreparedHallwithExh.close();


                PreparedStatement PreraredAddressPK = connExhibition.prepareStatement(SELECT_ID_ADDRESS);
                for (int i = 0; i < address.size(); i++) {
                    List<String> words = new ArrayList<>();
                    String[] WordsAddress = address.get(i).split(" ");
                    for (String word : WordsAddress) {
                        words.add(word);
                    }
                    PreraredAddressPK.setString(1, words.get(0));
                    PreraredAddressPK.setString(2, words.get(1));
                    PreraredAddressPK.setString(3, words.get(2));
                    PreraredAddressPK.setInt(4, Integer.parseInt(words.get(3)));
                    ResultSet SetAddressPK = PreraredAddressPK.executeQuery();
                    while (SetAddressPK.next()) {
                        AddressPK.add(SetAddressPK.getInt(1));
                    }
                }
                PreraredAddressPK.close();


                PreparedStatement PreparedAddresswithExh = connExhibition.prepareStatement(INSERT_EXHIBITION_WITH_ADDRESS);
                for (int i = 0; i < AddressPK.size(); i++) {
                    PreparedAddresswithExh.setInt(1, ExhibitionPK);
                    PreparedAddresswithExh.setInt(2, AddressPK.get(i));
                    PreparedAddresswithExh.execute();
                }
                PreparedAddresswithExh.close();


                PreparedStatement PreparedWorkArtPK = connExhibition.prepareStatement(SELECT_ID_WORK_ART);
                for (int i = 0; i < workArt.size(); i++) {
                    PreparedWorkArtPK.setString(1, workArt.get(i));
                    ResultSet SetArtPK = PreparedWorkArtPK.executeQuery();
                    while (SetArtPK.next()) {
                        WorkArtPK.add(SetArtPK.getInt(1));
                    }
                }
                PreparedWorkArtPK.close();

                PreparedStatement PreparedArtswithExh = connExhibition.prepareStatement(INSERT_EXPOSITION);
                for (int i = 0; i < WorkArtPK.size(); i++) {
                    PreparedArtswithExh.setInt(1, ExhibitionPK);
                    PreparedArtswithExh.setInt(2, WorkArtPK.get(i));
                    PreparedArtswithExh.execute();
                }
                PreparedArtswithExh.close();
                LOGGER.debug("AddFirstPage in debug");
                return true;
            } catch (Exception e) {
                LOGGER.error("AddFirstPage " + e.getMessage());
                connExhibition.rollback(savepointAdd);
            }
        } catch (Exception ex) {
            LOGGER.error("AddFirstPage " + ex.getMessage());
        }
        return false;
    }

    public static Boolean delFirstPage(String nameExibition) { //function dels exhibitions data
        try {
            Connection connExhibition = HikariConnectDB.getConnection();
            Savepoint savepointDel = connExhibition.setSavepoint("SavepointDel");
            try {
                PreparedStatement ExhibitionDel = connExhibition.prepareStatement(DELETE_EXHIBITION);
                ExhibitionDel.setString(1, nameExibition);
                Integer row = ExhibitionDel.executeUpdate();
                ExhibitionDel.close();

                if (row > 0) {
                    LOGGER.debug("DelFirstPage in debug");
                    return true;
                }
                LOGGER.debug("DelFirstPage in debug");
                return false;
            } catch (Exception e) {
                LOGGER.error("DelFirstPage " + e.getMessage());
                connExhibition.rollback(savepointDel);
            }
        } catch (Exception ex) {
            LOGGER.error("DelFirstPage " + ex.getMessage());
        }
        return false;
    }

    public static Boolean accessFirstPage(String nameExhibition, String access) { //function dels exhibitions data
        try {
            Connection connExhibition = HikariConnectDB.getConnection();
            Savepoint savepointAccess = connExhibition.setSavepoint("SavepointAccess");
            try {

                PreparedStatement statementChange = connExhibition.prepareStatement(UPDATE_EXHIBITION);
                statementChange.setString(1, access);
                statementChange.setString(2, nameExhibition);
                Integer row = statementChange.executeUpdate();
                statementChange.close();

                if (row > 0) {
                    LOGGER.debug("AccessFirstPage in debug");
                    return true;
                }
                LOGGER.debug("AccessFirstPage in debug");
                return false;
            } catch (Exception e) {
                LOGGER.error("AccessFirstPage " + e.getMessage());
                connExhibition.rollback(savepointAccess);
            }
        } catch (Exception ex) {
            LOGGER.error("AccessFirstPage " + ex.getMessage());
        }
        return false;
    }


}

