package Handler;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
 
public class CypherHandler {
    
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    public static final int DEF_SALT_LENGTH = 10;
    
     public static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a text: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static String generateHash(String text, String salt) {
        String returnValue = null;

        byte[] secureText = hash(text.toCharArray(), salt.getBytes());
 
        returnValue = Base64.getEncoder().encodeToString(secureText);
 
        return returnValue;
    }
    
    public static boolean verifyHash(String providedText,
            String hash, String salt)
    {
        boolean returnValue = false;

        String newSecureText = generateHash(providedText, salt);

        returnValue = newSecureText.equals(hash);
        
        return returnValue;
    }
}