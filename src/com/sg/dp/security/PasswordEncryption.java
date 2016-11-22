package com.sg.dp.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 11/2/2016.
 * This is an example of Symmetric encryption. Both happens using the same key below
 * Key and Algorithm are two aspects to encryption (SCEA). Key is the more important than
 * algorithm
 */
public class PasswordEncryption {
    private static final char[] KEY = "enfldsgbnlsngdlksdsgm".toCharArray();

    private static final byte[] SALT = {
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
    };

    public static final String ALGORITHM = "PBEWithMD5AndDES";

    public static void main(String[] args) throws Exception {
        String originalPassword = "secret";
        stdout("Original password: " + originalPassword);
        String encryptedPassword = encrypt(originalPassword);
        stdout("Encrypted password: " + encryptedPassword);
        String decryptedPassword = decrypt(encryptedPassword);
        stdout("Decrypted password: " + decryptedPassword);
    }

    private static String encrypt(String password) throws GeneralSecurityException,
            UnsupportedEncodingException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(KEY));
        Cipher pbeCipher = Cipher.getInstance(ALGORITHM);
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(password.getBytes("UTF-8")));
    }

    private static String decrypt(String encPassword) throws GeneralSecurityException,
            IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(KEY));
        Cipher pbeCipher = Cipher.getInstance(ALGORITHM);
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(encPassword)), "UTF-8");
    }

    //Using the Java8 Base64 encoder and decoder (below)
    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }

}
