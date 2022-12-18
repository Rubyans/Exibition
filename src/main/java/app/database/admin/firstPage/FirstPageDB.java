package app.database.admin.firstPage;

import app.entities.adminEntities.firstPage.AdminAddShow;
import app.entities.adminEntities.firstPage.AdminShow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FirstPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection conn;
    private static final Logger LOGGER = LogManager.getLogger(FirstPageDB.class);

    public static Connection checkConnection() {
        return conn;
    } //function checks connection

    public static void startConnnection() //function creates connection with DB
    {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    conn = DriverManager.getConnection(url);
                    conn.setAutoCommit(false);
                    savepoint = conn.setSavepoint("savepointMain"); //savepoint(transacion)
                    LOGGER.debug("startConnnection in debug");
                } catch (SQLException e) {
                    LOGGER.error("startConnnection " + e.getMessage());
                }
            } catch (Exception ex) {
                LOGGER.error("startConnnection " + ex.getMessage());
            }
        }
    }

    public static void nullConnection() {
        conn = null;
    } //function gives null value

    public static List<AdminShow> exibitionShow() { //function shows(SELECT) exhibitions data

        try {
            List<AdminShow> admin = new ArrayList<>();
            List<String> nameEx = new ArrayList<>();

            String nameExhibition = null;
            String descriptionExibition = null;
            String access=null;

            Double price = null;
            Date dateStart = null;
            Date dateEnd = null;

            List<String> expositionName = new ArrayList<>();
            List<String> nameHell = new ArrayList<>();
            List<String> nameAuthor = new ArrayList<>();
            List<String> nameview = new ArrayList<>();
            List<String> addressExibition = new ArrayList<>();


            PreparedStatement statement = conn.prepareStatement("SELECT name FROM exhibitiondb.exhibition", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setName = statement.executeQuery();
            while (setName.next()) {
                nameEx.add(setName.getString(1));
            }
            statement.close();

            for (String name : nameEx) {
                PreparedStatement resStatment = conn.prepareStatement("Select exhibition.name,description,work_art.name,price,\n" +
                        "date_start,date_end,access,hall.name,author.first_name,author.last_name,nameview,city,street_or_square,number_home\n" +
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
                        "where exhibition.name='" + name + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = resStatment.executeQuery();
                while (resultSet.next()) {
                    nameExhibition = resultSet.getString(1);
                    descriptionExibition = resultSet.getString(2);
                    expositionName.add(resultSet.getString(3));
                    price = resultSet.getDouble(4);
                    dateStart = resultSet.getDate(5);
                    dateEnd = resultSet.getDate(6);
                    access=resultSet.getString(7);
                    nameHell.add(resultSet.getString(8));
                    nameAuthor.add(resultSet.getString(9) + " " + resultSet.getString(10));
                    nameview.add(resultSet.getString(11));
                    addressExibition.add(resultSet.getString(12) + ", " + resultSet.getString(13) + " " + resultSet.getString(14));
                }
                if (nameExhibition != null)
                    admin.add(new AdminShow(nameExhibition, descriptionExibition, expositionName, price, dateStart, dateEnd,access, nameHell, nameAuthor, nameview, addressExibition));
                nameExhibition = null;
                descriptionExibition = null;
                access=null;
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

    public static List<AdminAddShow> ShowAddFirstPage() { //function shows information for <select></select>
        try {
            List<AdminAddShow> AdminAddFirstPage = new ArrayList<>();

            List<String> hall = new ArrayList<>();
            List<String> address = new ArrayList<>();
            List<String> art = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement("SELECT name FROM exhibitiondb.hall", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setHall = statement.executeQuery();
            while (setHall.next()) {
                hall.add(setHall.getString(1));
            }

            statement = conn.prepareStatement("SELECT country,city,street_or_square,number_home FROM exhibitiondb.exhibition_address", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setAddress = statement.executeQuery();
            while (setAddress.next()) {
                address.add(setAddress.getString(1) + " " + setAddress.getString(2) + " " + setAddress.getString(3) + " " + setAddress.getString(4));
            }

            statement = conn.prepareStatement("SELECT name FROM work_art", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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

    public static Boolean AddFirstPage(String nameExibition, String description, Double price,
                                       String start, String end, List<String> hall,
                                       List<String> address, List<String> workArt) { //function adds exhibitions-data
        try {
            Savepoint savepointAdd = conn.setSavepoint("SavepointAdd");
            try {
                Integer ExhibitionPK = 0;
                List<Integer> HellPK = new ArrayList<>();
                List<Integer> AddressPK = new ArrayList<>();
                List<Integer> WorkArtPK = new ArrayList<>();

                PreparedStatement ExhibitionADD = conn.prepareStatement("INSERT into exhibitiondb.exhibition (name,description,price,date_start,date_end,access) values (?,?,?,?,?,?)");
                ExhibitionADD.setString(1, nameExibition);
                ExhibitionADD.setString(2, description);
                ExhibitionADD.setDouble(3, price);
                ExhibitionADD.setString(4, start);
                ExhibitionADD.setString(5, end);
                ExhibitionADD.setInt(5, 1);
                ExhibitionADD.execute();
                ExhibitionADD.close();

                PreparedStatement PreparedExhPK = conn.prepareStatement("SELECT exhibition_id from exhibitiondb.exhibition where name=?");
                PreparedExhPK.setString(1, nameExibition);
                ResultSet ExhibitionPKSet = PreparedExhPK.executeQuery();
                while (ExhibitionPKSet.next()) {
                    ExhibitionPK = ExhibitionPKSet.getInt(1);
                }
                PreparedExhPK.close();


                PreparedStatement PreparedHallPK = conn.prepareStatement("SELECT hall_id from exhibitiondb.hall where name=?");
                for (int i = 0; i < hall.size(); i++) {
                    PreparedHallPK.setString(1, hall.get(i));
                    ResultSet SetHallPK = PreparedHallPK.executeQuery();
                    while (SetHallPK.next()) {
                        HellPK.add(SetHallPK.getInt(1));
                    }
                }
                PreparedHallPK.close();

                PreparedStatement PreparedHallwithExh = conn.prepareStatement("INSERT INTO exhibitiondb.exhibition_with_hall (hall_fk,exhi_fk) values (?,?)");
                for (int i = 0; i < HellPK.size(); i++) {
                    PreparedHallwithExh.setInt(1, HellPK.get(i));
                    PreparedHallwithExh.setInt(2, ExhibitionPK);
                    PreparedHallwithExh.execute();
                }
                PreparedHallwithExh.close();


                PreparedStatement PreraredAddressPK = conn.prepareStatement("SELECT address_id from exhibitiondb.exhibition_address where country=? and city=? and street_or_square=? and number_home=?");
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


                PreparedStatement PreparedAddresswithExh = conn.prepareStatement("INSERT INTO exhibitiondb.exhibition_with_address (ex_fk,address_fk) values (?,?)");
                for (int i = 0; i < AddressPK.size(); i++) {
                    PreparedAddresswithExh.setInt(1, ExhibitionPK);
                    PreparedAddresswithExh.setInt(2, AddressPK.get(i));
                    PreparedAddresswithExh.execute();
                }
                PreparedAddresswithExh.close();


                PreparedStatement PreparedWorkArtPK = conn.prepareStatement("SELECT art_id from exhibitiondb.work_art where name=?");
                for (int i = 0; i < workArt.size(); i++) {
                    PreparedWorkArtPK.setString(1, workArt.get(i));
                    ResultSet SetArtPK = PreparedWorkArtPK.executeQuery();
                    while (SetArtPK.next()) {
                        WorkArtPK.add(SetArtPK.getInt(1));
                    }
                }
                PreparedWorkArtPK.close();

                PreparedStatement PreparedArtswithExh = conn.prepareStatement("INSERT INTO exhibitiondb.exposition (exhibition_fk,art_fk) values (?,?)");
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
                conn.rollback(savepointAdd);
            }
        } catch (Exception ex) {
            LOGGER.error("AddFirstPage " + ex.getMessage());
        }
        return false;
    }

    public static Boolean DelFirstPage(String nameExibition) { //function dels exhibitions data
        try {
            Savepoint savepointDel = conn.setSavepoint("SavepointDel");
            try {

                PreparedStatement ExhibitionDel = conn.prepareStatement("DELETE FROM exhibitiondb.exhibition WHERE name=?");
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
                conn.rollback(savepointDel);
            }
        } catch (Exception ex) {
            LOGGER.error("DelFirstPage " + ex.getMessage());
        }
        return false;
    }
    public static Boolean AccessFirstPage(String nameExhibition,String access) { //function dels exhibitions data
        try {
            Savepoint savepointAccess = conn.setSavepoint("SavepointAccess");
            try {

                PreparedStatement statementChange = conn.prepareStatement("UPDATE exhibitiondb.exhibition SET access = ? WHERE name = ?");
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
                conn.rollback(savepointAccess);
            }
        } catch (Exception ex) {
            LOGGER.error("AccessFirstPage " + ex.getMessage());
        }
        return false;
    }

    public static boolean exitConnection() //function closes con
    {
        try {
            if (savepoint != null) {
                conn.rollback(savepoint);
                conn.commit();
                conn.close();
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

    public static void SaveCommit() { //function saves data
        try {
            conn.commit();
            savepoint = conn.setSavepoint("savepointMain");
            LOGGER.debug("SaveCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("SaveCommit " + e.getMessage());
        }
    }

    public static void RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                conn.rollback(savepoint);
            LOGGER.debug("RoleBackCommit in debug");
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit " + e.getMessage());
        }

    }


}

