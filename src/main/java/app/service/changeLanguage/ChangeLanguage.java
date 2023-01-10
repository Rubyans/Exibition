package app.service.changeLanguage;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChangeLanguage {

    private static final Logger LOGGER = Logger.getLogger(ChangeLanguage.class);

    public static List<String> changeEN(String property, String propText) {
        List<String> listEN = new ArrayList<>();

        try (InputStream input = ChangeLanguage.class.getClassLoader().getResourceAsStream(property)) {
            Properties props = new Properties();
            props.load(input);

            Integer count = 0;
            Boolean stopWhile = true;

            while (stopWhile) {
                count++;
                if (props.getProperty(propText + "." + count) == null)
                    stopWhile = false;
                else
                    listEN.add(props.getProperty(propText + "." + count));
            }
            return listEN;
        } catch (Exception e) {
            LOGGER.error("changeEN " + e.getMessage());
        }

        return null;
    }

    public static List<String> changeUA(String property, String propText) {
        List<String> listUA = new ArrayList<>();

        try (InputStream input = ChangeLanguage.class.getClassLoader().getResourceAsStream(property)) {
            Properties props = new Properties();
            props.load(input);

            Integer count = 0;
            Boolean stopWhile = true;

            while (stopWhile) {
                count++;
                if (props.getProperty(propText + "." + count) == null)
                    stopWhile = false;
                else
                    listUA.add(props.getProperty(propText + "." + count));
            }
            return listUA;
        } catch (Exception e) {
            LOGGER.error("changeUA " + e.getMessage());
        }

        return null;
    }

}
