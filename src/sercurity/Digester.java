package sercurity;

import logic.ConfigLoader;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by michaelfolkmann on 11/11/2016.
 */
public class Digester {
    private final static String KEY = "40674244454045cb9a70040a30e1c007";

    //hashing er ikke medtaget her.

    //Creating method that encrypts the data we are sending to server.
    //Returns encrypted data.
    public static String encrypt(String s) {
        String encrypted_string = s;

        if (ConfigLoader.ENCRYPTION.equals("TRUE")) {
            encrypted_string = base64Encode(xorWithKey(encrypted_string.getBytes(), KEY.getBytes()));

        }
        return encrypted_string;
    }

    //Creating method which decrypt data coming from server.
    public static String decrypt(String s) {
        String decrypted_string = s;

        if (ConfigLoader.ENCRYPTION.equals("TRUE")) {
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
