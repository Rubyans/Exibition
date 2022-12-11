package app.database.user.firstPage;

import app.entities.userEntities.firstPage.UserShowAdd;
import app.entities.userEntities.firstPage.UserShowExhibition;
import app.entities.userEntities.firstPage.UserShowMoney;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FirstPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connUserEx;

    public static Connection checkConnection() {
        return connUserEx;
    }

    public static void startConnnection() { //function creates connect with DB
        if (connUserEx == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connUserEx = DriverManager.getConnection(url);
                    connUserEx.setAutoCommit(false);
                    savepoint = connUserEx.setSavepoint("savepointMain");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void nullConnection() {
        connUserEx = null;
    }
    public static List<UserShowExhibition> userShowEx(String userId) { //function shows exhibition data
        try {
            try {
                List<UserShowExhibition> user = new ArrayList<>();

                String nameExhibition = null;
                Double price = null;
                Date dataStart = null;
                Date dateEnd = null;
                Integer valueTicket = null;

                PreparedStatement statementTicket = connUserEx.prepareStatement("SELECT exhibition.name FROM exhibitiondb.exhibition \n" +
                        "INNER JOIN exhibitiondb.ticket ON ticket_fk=exhibition_id\n" +
                        "INNER JOIN exhibitiondb.authorized_user ON user_id=user_fk\n" +
                        "WHERE authorized_user.user_id='" + userId + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                ResultSet setTicket = statementTicket.executeQuery();
                List<String> listNameExhibition = new ArrayList<>();
                HashMap<String, Integer> mapExhibition = new HashMap<String, Integer>();

                while (setTicket.next()) {
                    listNameExhibition.add(setTicket.getString(1));
                }

                for (String nameEx : listNameExhibition) {
                    if (mapExhibition.keySet().contains(nameEx)) {
                        mapExhibition.put(nameEx, mapExhibition.get(nameEx) + 1);
                    } else {
                        mapExhibition.put(nameEx, 1);
                    }
                }
                PreparedStatement statement = connUserEx.prepareStatement("SELECT name,price,date_start,date_end FROM exhibitiondb.exhibition", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setUser = statement.executeQuery();
                while (setUser.next()) {
                    nameExhibition = setUser.getString(1);
                    price = setUser.getDouble(2);
                    dataStart = setUser.getDate(3);
                    dateEnd = setUser.getDate(4);
                    for (String key : mapExhibition.keySet()) {
                        if (key.equals(nameExhibition))
                            valueTicket = mapExhibition.get(key);
                    }
                    user.add(new UserShowExhibition(nameExhibition, price, dataStart, dateEnd, valueTicket));
                    valueTicket = null;
                }
                statement.close();
                statementTicket.close();

                return user;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<UserShowAdd> ShowAddExhibition() { //function shows exhibitions data
        try {

            List<UserShowAdd> userAdd = new ArrayList<>();
            String nameExhibition = null;

            PreparedStatement statement = connUserEx.prepareStatement("SELECT name FROM exhibitiondb.exhibition", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setUserAdd = statement.executeQuery();
            while (setUserAdd.next()) {
                nameExhibition = setUserAdd.getString(1);
                userAdd.add(new UserShowAdd(nameExhibition));
            }
            statement.close();
            return userAdd;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<UserShowMoney> ShowMoney(String userId) { //function shows money of userAutorized
        try {

            List<UserShowMoney> userMoney = new ArrayList<>();
            Double amount = null;

            PreparedStatement statement = connUserEx.prepareStatement("SELECT amount FROM exhibitiondb.authorized_user where user_id='" + userId + "';", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet setUserMoney = statement.executeQuery();
            while (setUserMoney.next()) {
                amount = setUserMoney.getDouble(1);
                userMoney.add(new UserShowMoney(amount));
            }
            statement.close();
            return userMoney;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean ticketAdd(List<String> nameExhibition, String userId) { //function adds tickets data
        try {
            Savepoint savepointAdd = connUserEx.setSavepoint("SavepointAdd");
            try {

                Double amountUser = 0.0;
                Double amountExhibitions = 0.0;
                Double amountRes = 0.0;
                List<Integer> exhibitionId = new ArrayList<>();

                PreparedStatement statementAmount = connUserEx.prepareStatement("SELECT amount FROM exhibitiondb.authorized_user where user_id='" + userId + "';");
                ResultSet resultAmount = statementAmount.executeQuery();
                while (resultAmount.next()) {
                    amountUser = resultAmount.getDouble(1);
                }
                statementAmount.close();

                for (int i = 0; i < nameExhibition.size(); i++) {
                    PreparedStatement statementEx = connUserEx.prepareStatement("SELECT exhibition_id,price FROM exhibitiondb.exhibition where name='" + nameExhibition.get(i) + "';");
                    ResultSet resultEx = statementEx.executeQuery();
                    while (resultEx.next()) {
                        exhibitionId.add(resultEx.getInt(1));
                        amountExhibitions += resultEx.getDouble(2);
                    }
                    statementEx.close();
                }

                amountRes = amountUser - amountExhibitions;

                if (amountRes > 0 || amountRes == 0) {
                    PreparedStatement statementTicket = connUserEx.prepareStatement("INSERT INTO exhibitiondb.ticket (user_fk,ticket_fk) values (?,?)");
                    for (int i = 0; i < exhibitionId.size(); i++) {
                        statementTicket.setInt(1, Integer.parseInt(userId));
                        statementTicket.setInt(2, exhibitionId.get(i));
                        statementTicket.execute();
                    }
                    statementTicket.close();
                    PreparedStatement statementUpdate = connUserEx.prepareStatement("UPDATE exhibitiondb.authorized_user set amount = ? where user_id = ?");
                    statementUpdate.setDouble(1, amountRes);
                    statementUpdate.setInt(2, Integer.parseInt(userId));
                    statementUpdate.execute();
                    statementUpdate.close();
                    return true;
                } else
                    return false;
            } catch (Exception e) {
                e.printStackTrace();
                connUserEx.rollback(savepointAdd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean exitConnection() //function closes connect with DB
    {
        try {
            if (savepoint != null) {
                connUserEx.rollback(savepoint);
                connUserEx.commit();
                connUserEx.close();
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean SaveCommit() { //function saves commit
        try {
            connUserEx.commit();
            savepoint = connUserEx.setSavepoint("savepointMain");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null) {
                connUserEx.rollback(savepoint);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}