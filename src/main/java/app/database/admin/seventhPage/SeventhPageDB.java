package app.database.admin.seventhPage;


import app.entities.adminEntities.seventhPage.UserAutorizedShow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeventhPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;
    private static Connection connUserAutorized;

    public static Connection checkConnection() {
        return connUserAutorized;
    }

    public static void startConnnection() { //function creates connect with DB
        if (connUserAutorized == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connUserAutorized = DriverManager.getConnection(url);
                    connUserAutorized.setAutoCommit(false);
                    savepoint = connUserAutorized.setSavepoint("savepointMain");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void nullConnection() {
        connUserAutorized = null;
    }

    public static List<UserAutorizedShow> UserAuto() { //function shows userAutorized
        try {
            try {

                List<UserAutorizedShow> userAuto = new ArrayList<>();

                String fistName, lastName, login, password, email, role;
                Double amount;

                PreparedStatement statement = connUserAutorized.prepareStatement("SELECT first_name,last_name,login,password,email,amount,role FROM exhibitiondb.authorized_user", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setUser = statement.executeQuery();
                while (setUser.next()) {
                    fistName = setUser.getString(1);
                    lastName = setUser.getString(2);
                    login = setUser.getString(3);
                    password = setUser.getString(4);
                    email = setUser.getString(5);
                    amount = setUser.getDouble(6);
                    role = setUser.getString(7);

                    userAuto.add(new UserAutorizedShow(fistName, lastName, login, password, email, amount, role));
                }
                statement.close();
                return userAuto;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Boolean UserAutoAdd(String fistName, String lastName, String login, String password, String email, Double amount, String role) { //function adds UserAutorized
        try {
            Savepoint savepointAdd = connUserAutorized.setSavepoint("SavepointAdd");
            try {
                PreparedStatement UserAdd = connUserAutorized.prepareStatement("INSERT into exhibitiondb.authorized_user (first_name,last_name,login,password,email,amount,role) values (?,?,?,?,?,?,?)");
                UserAdd.setString(1, fistName);
                UserAdd.setString(2, lastName);
                UserAdd.setString(3, login);
                UserAdd.setString(4, password);
                UserAdd.setString(5, email);
                UserAdd.setDouble(6, amount);
                UserAdd.setString(7, role);
                UserAdd.execute();
                UserAdd.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                connUserAutorized.rollback(savepointAdd);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    public static Boolean UserAmountAdd(String email, Double amount) { //function adds Amount for UserAutorized
        try {
            Savepoint SavepointAmount = connUserAutorized.setSavepoint("SavepointAmount");
            try {
                PreparedStatement AmountAdd = connUserAutorized.prepareStatement("UPDATE exhibitiondb.authorized_user SET amount = ? WHERE email = ?");
                AmountAdd.setDouble(1, amount);
                AmountAdd.setString(2, email);
                AmountAdd.execute();
                AmountAdd.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                connUserAutorized.rollback(SavepointAmount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean UserAutoDel(String email) { //function deletes userAutorized data
        try {
            Savepoint savepointDel = connUserAutorized.setSavepoint("SavepointDel");
            try {
                PreparedStatement userDel = connUserAutorized.prepareStatement("DELETE FROM exhibitiondb.authorized_user WHERE email=?");
                userDel.setString(1, email);
                int row = userDel.executeUpdate();
                userDel.close();

                if (row > 0)
                    return true;
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                connUserAutorized.rollback(savepointDel);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    public static boolean exitConnection() { //function closes connect
        try {
            if (savepoint != null) {
                connUserAutorized.rollback(savepoint);
                connUserAutorized.commit();
                connUserAutorized.close();
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveCommit() { //function saves data
        try {
            connUserAutorized.commit();
            savepoint = connUserAutorized.setSavepoint("savepointMain");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                connUserAutorized.rollback(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
