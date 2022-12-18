package app.database.encrypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class EncryptforDB {
    private static final Logger LOGGER = LogManager.getLogger(EncryptforDB.class);

    public static String encrypt(String text) // function encodes the password in sha-1
    {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(text.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
            LOGGER.debug("encrypt in debug");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("encrypt " + e.getMessage());
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error("encrypt " + ex.getMessage());
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) // function convert bytes to hexadecimal
    {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
