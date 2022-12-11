package app.database.admin.sixthPage;


import app.entities.adminEntities.sixthPage.ViewShow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SixthPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;

    private static Connection connView;

    public static Connection checkConnection() {
        return connView;
    }

    public static void startConnnection() { //function creates connect with DB
        if (connView == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connView = DriverManager.getConnection(url);
                    connView.setAutoCommit(false);
                    savepoint = connView.setSavepoint("savepointMain");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void nullConnection() {
        connView = null;
    } //function gives a value of null

    public static List<ViewShow> viewShow() { //function shows views data
        try {
            try {
                List<ViewShow> view = new ArrayList<>();
                String name;
                Integer viewId;

                PreparedStatement statement = connView.prepareStatement("SELECT * FROM exhibitiondb.view_art", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setView = statement.executeQuery();
                while (setView.next()) {
                    viewId = setView.getInt(1);
                    name = setView.getString(2);
                    view.add(new ViewShow(viewId, name));
                }
                statement.close();
                return view;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Boolean viewAdd(String name) { //function adds views data
        try {
            Savepoint savepointAdd = connView.setSavepoint("SavepointAdd");
            try {
                PreparedStatement viewAdd = connView.prepareStatement("INSERT into exhibitiondb.view_art (nameview) values (?)");
                viewAdd.setString(1, name);
                viewAdd.execute();
                viewAdd.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                connView.rollback(savepointAdd);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    public static Boolean viewDel(String email) { //function deletes views data
        try {
            Savepoint savepointDel = connView.setSavepoint("SavepointDel");
            try {
                PreparedStatement viewDel = connView.prepareStatement("DELETE FROM exhibitiondb.view_art WHERE nameview=?");
                viewDel.setString(1, email);
                Integer row = viewDel.executeUpdate();
                viewDel.close();

                if (row > 0)
                    return true;
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                connView.rollback(savepointDel);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    public static boolean exitConnection() { //function closes connect with DB
        try {
            if (savepoint != null) {
                connView.rollback(savepoint);
                connView.commit();
                connView.close();
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
            connView.commit();
            savepoint = connView.setSavepoint("savepointMain");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                connView.rollback(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
