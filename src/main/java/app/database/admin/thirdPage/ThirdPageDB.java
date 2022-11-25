package app.database.admin.thirdPage;

import app.entities.adminEntities.secondPage.HallShow;
import app.entities.adminEntities.thirdPage.AddressShow;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThirdPageDB
{
    private static String url = "jdbc:mysql://localhost/exhibitiondb?user=root&password=root";
    private static Savepoint savepoint;

    private static Connection connAddress;

    static {
        try {
            connAddress = DriverManager.getConnection(url);
            connAddress.setAutoCommit(false);
            savepoint = connAddress.setSavepoint("savepointMain");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<AddressShow> addressShow() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {

                List<AddressShow> address = new ArrayList<>();

                int Unumber;
                String country = null;
                String city=null;
                String street=null;
                int numberHouse;

                PreparedStatement statement = connAddress.prepareStatement("SELECT* FROM exhibitiondb.exhibition_address", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet setAddress = statement.executeQuery();
                while (setAddress.next()) {
                    Unumber=setAddress.getInt(1);
                    country=setAddress.getString(2);
                    city=setAddress.getString(3);
                    street=setAddress.getString(4);
                    numberHouse=setAddress.getInt(5);
                    address.add(new AddressShow(Unumber,country,city,street,numberHouse));
                }
                statement.close();


                return address;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static Boolean addressAdd(String country,String city,String street,int numberHouse) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Savepoint savepointAdd = connAddress.setSavepoint("SavepointAdd");
            try {

                PreparedStatement AddressAdd = connAddress.prepareStatement("INSERT into exhibitiondb.exhibition_address (country,city,street_or_square,number_home) values (?,?,?,?)");
                AddressAdd.setString(1, country);
                AddressAdd.setString(2, city);
                AddressAdd.setString(3, street);
                AddressAdd.setInt(4, numberHouse);
                AddressAdd.execute();
                AddressAdd.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                connAddress.rollback(savepointAdd);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }
    public static Boolean addressDel(int Unumber) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Savepoint savepointDel = connAddress.setSavepoint("SavepointDel");
            try {

                PreparedStatement AddressDel = connAddress.prepareStatement("DELETE FROM exhibitiondb.exhibition_address WHERE address_id=?");
                AddressDel.setInt(1, Unumber);
                int row = AddressDel.executeUpdate();
                AddressDel.close();

                if (row > 0)
                    return true;
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                connAddress.rollback(savepointDel);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }
    public static void saveCommit() {
        try {
            connAddress.commit();
            savepoint=connAddress.setSavepoint("savepointMain");
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
                connAddress.rollback(savepoint);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


}
