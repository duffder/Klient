package sercurity;

import logic.ConfigLoader;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;

/**
 * Created by michaelfolkmann on 11/11/2016.
 */
public class Digester {
    private final static String SALT = ConfigLoader.HASH_SALT;
    private final static String KEY = ConfigLoader.ENCRYPT_KEY;
    private static MessageDigest digester;

    //hashing with MD5
    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //Hashing of String
    public static String hash(String str){
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Error");
        }
        return Digester._hash(str);
    }
    //hashing with salt
    public static String hashWithSalt(String str){
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Error");
        }
        str = str + Digester.SALT;
        return Digester._hash(str);
    }
    //Convert hash value to hexidecimals
    private static String _hash(String str) {
        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (byte aHash : hash) {
            if ((0xff & aHash) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & aHash)));
            } else {
                hexString.append(Integer.toHexString(0xFF & aHash));
            }
        }
        return hexString.toString();
    }

    //Creating method that encrypts the data we are sending to server.
    //Returns encrypted data.
    public static String encrypt(String s) {
        String encrypted_string = s;

        if (ConfigLoader.ENCRYPTION.equals(ConfigLoader.ENCRYPTION)) {
            encrypted_string = base64Encode(xorWithKey(encrypted_string.getBytes(), KEY.getBytes()));

        }
        return encrypted_string;
    }

    //Creating method which decrypt data coming from server.
    public static String decrypt(String s) {
        String decrypted_string = s;

        if (ConfigLoader.ENCRYPTION.equals(ConfigLoader.ENCRYPTION)) {
            decrypted_string = new String(xorWithKey(base64Decode(s), KEY.getBytes()));
        }
        return decrypted_string;
    }

    //Method that encrypts.
    private static String base64Encode(byte[] bytes) {
        BASE64Encoder enc = new BASE64Encoder();
        return enc.encode(bytes).replaceAll("\\s", "");

    }
    //Method that decrypts.
    private static byte[] base64Decode(String s) {
        try {
            BASE64Decoder d = new BASE64Decoder();
            return d.decodeBuffer(s);
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    private static byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i%key.length]);
        }
        return out;
    }
}
