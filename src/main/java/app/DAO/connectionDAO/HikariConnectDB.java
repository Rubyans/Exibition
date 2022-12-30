package app.DAO.connectionDAO;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

public class HikariConnectDB {

    private static final Logger LOGGER = Logger.getLogger(HikariConnectDB.class);

    private static Connection connectionDB;
    private static Savepoint savepoint;

    public HikariConnectDB() {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Properties prop = new Properties();
            prop.load(input); // load a properties file

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(prop.getProperty("db.host"));
            config.setUsername(prop.getProperty("db.login"));
            config.setPassword(prop.getProperty("db.password"));
            config.addDataSourceProperty("minimumIdle", "5");
            config.addDataSourceProperty("maximumPoolSize", "25");
            HikariDataSource dataSource = new HikariDataSource(config);
            connectionDB = dataSource.getConnection();
            connectionDB.setAutoCommit(false);
            savepoint = connectionDB.setSavepoint("savepointMain"); //savepoint(transacion)
        } catch (Exception ex) {
            LOGGER.error("HikariConnectDB " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return connectionDB;
    }

    public static boolean checkConnection() {
        try {
            if (connectionDB != null)
                if (connectionDB.isClosed() != true)
                    return true;
            return false;
        } catch (Exception e) {
            LOGGER.error("checkConnection " + e.getMessage());

        }
        return false;
    }

    public static void exitConnection() {
        try {
            if (connectionDB != null)
                if (connectionDB.isClosed() != true) {
                    connectionDB.rollback(savepoint);
                    connectionDB.close();
                }
            LOGGER.debug("exitConnection in debug");
        } catch (SQLException e) {
            LOGGER.error("exitConnection " + e.getMessage());
        }
    }

    public static boolean saveCommit() { //function saves data
        try {
            connectionDB.commit();
            savepoint = connectionDB.setSavepoint("savepointMain");
            LOGGER.debug("SaveCommit in debug");
            return true;
        } catch (SQLException e) {
            LOGGER.error("SaveCommit " + e.getMessage());
        }
        return false;
    }

    public static boolean roleBackCommit() { //function roleback data
        try {
            if (savepoint != null)
                connectionDB.rollback(savepoint);
            LOGGER.debug("RoleBackCommit in debug");
            return true;
        } catch (SQLException e) {
            LOGGER.error("RoleBackCommit " + e.getMessage());
        }

        return false;
    }
}
