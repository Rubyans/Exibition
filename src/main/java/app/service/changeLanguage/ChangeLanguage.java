package app.service.changeLanguage;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChangeLanguage {

    private static final Logger LOGGER = Logger.getLogger(ChangeLanguage.class);

    public static List<String> changeEN(String property,String propText) {
        List<String> listEn = new ArrayList<>();

        try (InputStream input = ChangeLanguage.class.getClassLoader().getResourceAsStream(property)) {

            Properties props = new Properties();
            props.load(input);

            Integer countText = 0, count = 1;
            Boolean stopWhile=true;

            while (stopWhile) {
                count++;
                if (props.getProperty("ua." + propText + "." + count) == null) {
                    stopWhile=false;
                }
                countText++;
            }

            for (int i = 1; i <= countText; i++) {
                listEn.add(props.getProperty("en." + propText + "." + i));
            }
            return listEn;
        } catch (Exception e) {
            LOGGER.error("changeEN " + e.getMessage());
        }

        return null;
    }

    public static List<String> changeUA(String property, String propText) {
        List<String> listEn = new ArrayList<>();

        try (InputStream input = ChangeLanguage.class.getClassLoader().getResourceAsStream(property)) {
            Properties props = new Properties();
            props.load(input);

            Integer countText = 0, count = 0;
            Boolean stopWhile=true;

            while (stopWhile) {
                count++;
                if (props.getProperty("ua." + propText + "." + count) == null) {
                    stopWhile=false;
                }
                countText++;
            }
            for (int i = 1; i <= countText; i++) {
                listEn.add(props.getProperty("ua." + propText + "." + i));
            }
            return listEn;
        } catch (Exception e) {
            LOGGER.error("changeUA " + e.getMessage());
        }

        return null;
    }

}
