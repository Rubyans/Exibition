package app.database.admin.fifthPage;

import app.entities.adminEntities.fifthPage.ArtShow;
import app.entities.adminEntities.fourthPage.AuthorShow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FifthPageDB
{
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;

    private static Connection connArt;

    public static Connection checkConnection()
    {
        return connArt;
    }
    public static void startConnnection()
    {
        if(connArt==null)
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try {
                    connArt = DriverManager.getConnection(url);
                    connArt.setAutoCommit(false);
                    savepoint = connArt.setSavepoint("savepointMain");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void nullConnection()
    {
        connArt=null;
    }

    public static List<ArtShow> artShow() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {

                List<ArtShow> art = new ArrayList<>();


                String name;
                int yearCreation;
                Double price;

                PreparedStatement statement = connArt.prepareStatement("SELECT name,creation_year,appraised_value FROM exhibitiondb.work_art", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setArt = statement.executeQuery();
                while (setArt.next()) {
                    name=setArt.getString(1);
                    yearCreation=setArt.getInt(2);
                    price=setArt.getDouble(3);

                    art.add(new ArtShow(name,yearCreation,price));
                }
                statement.close();


                return art;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static Boolean artAdd(String name,int yearCreation,Double Price) {
        try {
            Savepoint savepointAdd = connArt.setSavepoint("SavepointAdd");
            try {

                PreparedStatement artAdd = connArt.prepareStatement("INSERT into exhibitiondb.work_art (name,creation_year,appraised_value) values (?,?,?)");
                artAdd.setString(1, name);
                artAdd.setInt(2, yearCreation);
                artAdd.setDouble(3, Price);
                artAdd.execute();
                artAdd.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                connArt.rollback(savepointAdd);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }
    public static Boolean artDel(String email) {
        try {
            Savepoint savepointDel = connArt.setSavepoint("SavepointDel");
            try {

                PreparedStatement artDel = connArt.prepareStatement("DELETE FROM exhibitiondb.work_art WHERE name=?");
                artDel.setString(1, email);
                int row = artDel.executeUpdate();
                artDel.close();

                if (row > 0)
                    return true;
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                connArt.rollback(savepointDel);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }
    public static void saveCommit() {
        try {
            connArt.commit();
            savepoint=connArt.setSavepoint("savepointMain");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public static void RoleBackCommit() {
        try
        {
            if(savepoint!=null)
                connArt.rollback(savepoint);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
