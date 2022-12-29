package app.service.encrypt;

import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Properties;

import org.apache.commons.codec.binary.Hex;

public class Encrypt {

    //AES - Advanced Encryption Standart, size block 128 bit, key 128/192/256 bit;
    //CBC - Cipher Block Chaining Mode - encryption block compression mode(режим зщеплення блоків шифрування);
    //PKCS5Padding - adds non-significant data to incomplete blocks(додає до неповних блоків незначущі дані);
    //IvParameterSpec - vector needed to initialize and mix with the first cipher block to eliminate the risk of compromise of identical data;
    //IvParameterSpec - вектор, потрібен для ініціалізації та змішування з першим блоком шифрування в цілях усунення небезпеки із-за компроментації одинакових даних.
    private static final Logger LOGGER = Logger.getLogger(Encrypt.class);
    private static final String INIT_VECTOR = "ItsOurExhibition";

    public static String encryptText(String text) {
        try (InputStream input = ChangeLanguage.class.getClassLoader().getResourceAsStream("secretKey.properties")) {

            Properties props = new Properties();
            props.load(input);

            String secretKey = props.getProperty("aes.key");

            IvParameterSpec ivParameterSpec = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);


            return new String(Hex.encodeHex(cipher.doFinal(text.getBytes("UTF-8")), false));

        } catch (Exception e) {
            LOGGER.error("encryptText" + e.getMessage());
        }
        return text;
    }

    public static String decryptText(String cipherText) {
        try (InputStream input = ChangeLanguage.class.getClassLoader().getResourceAsStream("secretKey.properties")) {

            Properties props = new Properties();
            props.load(input);

            String secretKey = props.getProperty("aes.key");

            IvParameterSpec ivParameterSpec = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);


            return new String(cipher.doFinal(Hex.decodeHex(cipherText.toCharArray())));

        } catch (Exception e) {
            LOGGER.error("decryptText" + e.getMessage());
        }
        return cipherText;
    }

}
