package app.database.admin.fourthPage;

import app.entities.adminEntities.fourthPage.AuthorShow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FourthPageDB {
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;

    private static Connection connAuthor;

    public static Connection checkConnection() {
        return connAuthor;
    }

    public static void startConnnection() //function creates connect with DB
    {
        if (connAuthor == null) {
            try {
                try {
                    connAuthor = DriverManager.getConnection(url);
                    connAuthor.setAutoCommit(false);
                    savepoint = connAuthor.setSavepoint("savepointMain");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void nullConnection() {
        connAuthor = null;
    }

    public static List<AuthorShow> authorShow() { //function shows author
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
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


                return author;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                connAuthor.rollback(savepointAdd);
            }
        } catch (Exception e) {

            e.printStackTrace();
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

                if (row > 0)
                    return true;
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                connAuthor.rollback(savepointDel);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    public static boolean exitConnection() { //function closes commit
        try {
            if (savepoint != null) {
                connAuthor.rollback(savepoint);
                connAuthor.commit();
                connAuthor.close();
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveCommit() { //function saves commit
        try {
            connAuthor.commit();
            savepoint = connAuthor.setSavepoint("savepointMain");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void RoleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                connAuthor.rollback(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
