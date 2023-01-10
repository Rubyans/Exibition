package app.service.recoveryPassword;

import java.security.SecureRandom;


public class GeneratePassword {

    public static String passwordRandom() {
        // each iteration of the loop randomly selects a character from the given
        // an ASCII range and append it to the `StringBuilder` instance
        Integer randNumOrigin = 48;
        Integer randNumBound = 57;
        Integer length = 8;
        SecureRandom random = new SecureRandom();
        return random.ints(randNumOrigin, randNumBound + 1)
                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}
