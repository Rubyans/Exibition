package app.DAO.sqlFunctions.sqlRequests;

public abstract class SQLRequests {

    public static class FirstPageAdmin {
        public static final String SELECT_NAME_EXHIBITION = "SELECT name FROM exhibitiondb.exhibition";
        public static final String SELECT_EXHIBITION = "SELECT exhibition.name,description,work_art.name,price,date_start,date_end," +
                "access,hall.name,author.first_name,author.last_name,nameview,city,street_or_square,number_home\n" +
                "FROM exhibitiondb.exhibition INNER JOIN exhibitiondb.exposition ON exhibition.exhibition_id=exposition.exhibition_fk\n" +
                "INNER JOIN exhibitiondb.work_art ON exposition.art_fk=work_art.art_id\n" +
                "INNER JOIN exhibitiondb.view_workart ON view_workart.artview_fk=work_art.art_id\n" +
                "INNER JOIN exhibitiondb.view_art ON view_art.view_id=view_workart.view_fk\n" +
                "INNER JOIN exhibitiondb.author_workart ON work_art.art_id=author_workart.wart_fk\n" +
                "INNER JOIN exhibitiondb.author ON author.author_id=author_workart.author_fk\n" +
                "INNER JOIN exhibitiondb.exhibition_with_address ON exhibition.exhibition_id=exhibition_with_address.ex_fk\n" +
                "INNER JOIN exhibitiondb.exhibition_address ON exhibition_address.address_id=exhibition_with_address.address_fk\n" +
                "INNER JOIN exhibitiondb.exhibition_with_hall ON exhibition.exhibition_id=exhibition_with_hall.exhi_fk\n" +
                "INNER JOIN exhibitiondb.hall ON hall.hall_id=exhibition_with_hall.hall_fk\n" +
                "WHERE exhibition.name=";
        public static final String SELECT_NAME_HALL = "SELECT name FROM exhibitiondb.hall";
        public static final String SELECT_ADDRESS = "SELECT country,city,street_or_square,number_home FROM exhibitiondb.exhibition_address";
        public static final String SELECT_WORK_ART = "SELECT name FROM work_art";
        public static final String SELECT_ID_EXHIBITION = "SELECT exhibition_id from exhibitiondb.exhibition where name=?";
        public static final String SELECT_ID_HALL = "SELECT hall_id from exhibitiondb.hall where name=?";
        public static final String SELECT_ID_ADDRESS = "SELECT address_id from exhibitiondb.exhibition_address WHERE country=? and city=? and street_or_square=? and number_home=?";
        public static final String SELECT_ID_WORK_ART = "SELECT art_id from exhibitiondb.work_art where name=?";
        public static final String INSERT_EXHIBITION = "INSERT INTO exhibitiondb.exhibition (name,description,price,date_start,date_end,access) VALUES (?,?,?,?,?,?)";
        public static final String INSERT_EXHIBITION_WITH_HALL = "INSERT INTO exhibitiondb.exhibition_with_hall (hall_fk,exhi_fk) VALUES (?,?)";
        public static final String INSERT_EXHIBITION_WITH_ADDRESS = "INSERT INTO exhibitiondb.exhibition_with_address (ex_fk,address_fk) VALUES (?,?)";
        public static final String INSERT_EXPOSITION = "INSERT INTO exhibitiondb.exposition (exhibition_fk,art_fk) VALUES (?,?)";
        public static final String DELETE_EXHIBITION = "DELETE FROM exhibitiondb.exhibition WHERE name=?";
        public static final String UPDATE_EXHIBITION = "UPDATE exhibitiondb.exhibition SET access = ? WHERE name = ?";
    }

    public static class SecondPageAdmin {
        public static final String SELECT_HALL = "SELECT name,square FROM exhibitiondb.hall";
        public static final String INSERT_HALL = "INSERT INTO exhibitiondb.hall (name,square) VALUES (?,?)";
        public static final String DELETE_HALL = "DELETE FROM exhibitiondb.hall WHERE name=?";
    }

    public static class ThirdPageAdmin {
        public static final String SELECT_ADDRESS = "SELECT * FROM exhibitiondb.exhibition_address";
        public static final String INSERT_ADDRESS = "INSERT INTO exhibitiondb.exhibition_address (country,city,street_or_square,number_home) VALUES (?,?,?,?)";
        public static final String DELETE_ADDRESS = "DELETE FROM exhibitiondb.exhibition_address WHERE address_id=?";
    }

    public static class FourthPageAdmin {
        public static final String SELECT_AUTHOR = "SELECT first_name,last_name,email FROM exhibitiondb.author";
        public static final String INSERT_AUTHOR = "INSERT INTO exhibitiondb.author (first_name,last_name,email) VALUES (?,?,?)";
        public static final String DELETE_AUTHOR = "DELETE FROM exhibitiondb.author WHERE email=?";
    }

    public static class FifthPageAdmin {
        public static final String SELECT_WORK_ART = "SELECT name FROM exhibitiondb.work_art";
        public static final String SELECT_FULL_WORK_ART = "SELECT name,creation_year,appraised_value,nameview,first_name,last_name,email\n" +
                "FROM exhibitiondb.work_art \n" +
                "INNER JOIN exhibitiondb.author_workart ON work_art.art_id=author_workart.wart_fk\n" +
                "INNER JOIN exhibitiondb.author ON author_workart.author_fk=author.author_id\n" +
                "INNER JOIN exhibitiondb.view_workart ON work_art.art_id=view_workart.artview_fk\n" +
                "INNER JOIN exhibitiondb.view_art ON view_workart.view_fk=view_art.view_id \n" +
                "WHERE name=";
        public static final String SELECT_AUTHOR = "SELECT first_name,last_name,email FROM exhibitiondb.author";
        public static final String SELECT_VIEW_ART = "SELECT nameview FROM exhibitiondb.view_art";
        public static final String SELECT_ID_AUTHOR = "SELECT author_id FROM exhibitiondb.author WHERE email=";
        public static final String SELECT_ID_VIEW_ART = "SELECT view_id FROM exhibitiondb.view_art WHERE nameview=";
        public static final String SELECT_ID_WORK_ART = "SELECT art_id FROM exhibitiondb.work_art WHERE name=";

        public static final String INSERT_WORK_ART = "INSERT INTO exhibitiondb.work_art (name,creation_year,appraised_value) VALUES (?,?,?)";
        public static final String INSERT_AUTHOR_WORKART = "INSERT INTO exhibitiondb.author_workart (author_fk,wart_fk) VALUES (?,?)";
        public static final String INSERT_VIEW_WORKART = "INSERT into exhibitiondb.view_workart (artview_fk,view_fk) VALUES (?,?)";
        public static final String DELETE_WORK_ART = "DELETE FROM exhibitiondb.work_art WHERE name=?";
    }

    public static class SixthPageAdmin {
        public static final String SELECT_VIEW_ART = "SELECT * FROM exhibitiondb.view_art";
        public static final String INSERT_VIEW_ART = "INSERT INTO exhibitiondb.view_art (nameview) VALUES (?)";
        public static final String DELETE_VIEW_ART = "DELETE FROM exhibitiondb.view_art WHERE nameview=?";
    }

    public static class SeventhPageAdmin {
        public static final String SELECT_USER_AUTHORIZED = "SELECT first_name,last_name,login,password,email,amount,role,access FROM exhibitiondb.authorized_user";
        public static final String INSERT_USER_AUTHORIZED = "INSERT INTO exhibitiondb.authorized_user (first_name,last_name,login,password,email,amount,role) VALUES (?,?,?,?,?,?,?)";
        public static final String UPDATE_USER_AUTHORIZED = "UPDATE exhibitiondb.authorized_user SET amount = ? WHERE email = ?";
        public static final String DELETE_USER_AUTHORIZED = "DELETE FROM exhibitiondb.authorized_user WHERE email=?";
        public static final String UPDATE_USER_AUTHORIZED_ACCESS = "UPDATE exhibitiondb.authorized_user SET access = ? WHERE email = ?";
    }

    public static class EighthPageAdmin {
        public static final String SELECT_NAME_EXHIBITION = "SELECT name FROM exhibitiondb.exhibition";
        public static final String SELECT_COUNT_TICKET = "SELECT COUNT(user_fk)\n" +
                "FROM exhibitiondb.ticket INNER JOIN exhibitiondb.exhibition ON ticket.ticket_fk=exhibition.exhibition_id\n" +
                "WHERE exhibition.name=";
    }

    public static class FirstPageUser {
        public static final String SELECT_NAME_EXHIBITION_INNER = "SELECT exhibition.name FROM exhibitiondb.exhibition\n" +
                "INNER JOIN exhibitiondb.ticket ON ticket_fk=exhibition_id\n" +
                "INNER JOIN exhibitiondb.authorized_user ON user_id=user_fk\n" +
                "WHERE authorized_user.user_id=";
        public static final String SELECT_EXHIBITION = "SELECT name,price,date_start,date_end FROM exhibitiondb.exhibition WHERE exhibition.access='1';";
        public static final String SELECT_NAME_EXHIBITION = "SELECT name FROM exhibitiondb.exhibition";
        public static final String SELECT_AMOUNT = "SELECT amount FROM exhibitiondb.authorized_user where user_id=";
        public static final String SELECT_ID_EXHIBITION = "SELECT exhibition_id,price FROM exhibitiondb.exhibition WHERE name=";
        public static final String INSERT_TICKET = "INSERT INTO exhibitiondb.ticket (user_fk,ticket_fk) VALUES (?,?)";
        public static final String UPDATE_USER_AUTHORIZED = "UPDATE exhibitiondb.authorized_user SET amount = ? where user_id = ?";

    }

    public static class SecondPageUser {
        public static final String SELECT_NAME_EXHIBITION = "SELECT name FROM exhibitiondb.exhibition\n" +
                "INNER JOIN exhibitiondb.ticket ON exhibition.exhibition_id=ticket.ticket_fk \n" +
                "INNER JOIN exhibitiondb.authorized_user ON authorized_user.user_id=ticket.user_fk\n" +
                "WHERE authorized_user.user_id=";
        public static final String SELECT_EXHIBITION = "SELECT exhibition.name,description,work_art.name,price,\n" +
                "date_start,date_end,hall.name,author.first_name,author.last_name,nameview,city,street_or_square,number_home\n" +
                "FROM exhibitiondb.exhibition INNER JOIN exhibitiondb.exposition ON exhibition.exhibition_id=exposition.exhibition_fK\n" +
                "INNER JOIN exhibitiondb.work_art ON exposition.art_fk=work_art.art_id\n" +
                "INNER JOIN exhibitiondb.view_workart ON view_workart.artview_fk=work_art.art_id\n" +
                "INNER JOIN exhibitiondb.view_art ON view_art.view_id=view_workart.view_fk\n" +
                "INNER JOIN exhibitiondb.author_workart ON work_art.art_id=author_workart.wart_fK\n" +
                "INNER JOIN exhibitiondb.author ON author.author_id=author_workart.author_fk\n" +
                "INNER JOIN exhibitiondb.exhibition_with_address ON exhibition.exhibition_id=exhibition_with_address.ex_fk\n" +
                "INNER JOIN exhibitiondb.exhibition_address ON exhibition_address.address_id=exhibition_with_address.address_fk\n" +
                "INNER JOIN exhibitiondb.exhibition_with_hall ON exhibition.exhibition_id=exhibition_with_hall.exhi_fk\n" +
                "INNER JOIN exhibitiondb.hall ON hall.hall_id=exhibition_with_hall.hall_fk\n" +
                "where exhibition.access='1' AND exhibition.name=";

    }

    public static class UserAuthorized {
        public static final String SELECT_USER_AUTORIZED = "SELECT user_id,first_name,last_name,email,role,access FROM exhibitiondb.authorized_user WHERE login = ? AND password = ?";
    }

    public static class UserRegistration {
        public static final String INSERT_USER_AUTORIZED = "INSERT exhibitiondb.authorized_user(first_name, last_name, login, password, email, role, access) VALUES (?,?,?,?,?,?,?)";
    }

    public static class UserGuest {
        public static final String SELECT_EXHIBITION = "SELECT exhibition.name,description,work_art.name,price,date_start,date_end\n" +
                "FROM exhibitiondb.exhibition INNER JOIN exhibitiondb.exposition ON exhibition.exhibition_id=exposition.exhibition_fk \n" +
                "INNER JOIN exhibitiondb.work_art ON exposition.art_fk=work_art.art_id WHERE exhibition.access='1';";
    }
}
