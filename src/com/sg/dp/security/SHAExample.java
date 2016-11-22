package com.sg.dp.security;

/**
 * Created by chandrashekar on 11/1/2016.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sg.dp.log.Logger.print;
import static com.sg.dp.log.Logger.stdout;

/**
 * Just an example to demonstrate the ONE WAY Hash Algorithm for storing passwords
 * and authenticating later
 */
public class SHAExample {
    private static final String passwordToHash = "password";
    private static final String wrongPassword = "password1";

    /** Arranged in the order of strength */
    private static final String [] algorithms = {"SHA-1", "SHA-256", "SHA-384", "SHA-512"};
    private static final byte [] salt = "abc".getBytes(); //Just using a constant salt rather than a random one

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        byte[] salt = getSalt();

        List<String> passwords = Arrays.stream(algorithms).map(sha -> {
            String shaPasswd = get_SHA_SecurePassword(passwordToHash, salt, sha);
            stdout("Hashed and salted Password using the algorithm " + sha + " is " + shaPasswd);
            return shaPasswd;
        }).collect(Collectors.toList()); //Collecting the passwds to authenticate later

        authenticate(algorithms[2], passwords);
    }

    private static void authenticate(String algorithm, List<String> passwords) {
        String userPassword = get_SHA_SecurePassword(passwordToHash, salt, algorithm);
        Optional<String> matchedPasswd = passwords.stream().filter(passwd -> passwd.equals(userPassword)).findFirst();

        if(matchedPasswd.isPresent()) print("User password matched with " + matchedPasswd.get());
        else print("User is NOT authenticated."); //Keep error msgs stingy -:) so a hacker can't crack in easily
    }

    /**
     * Step1: Get Alogorithm, Step2: using the algo instance, update the salt,
     * Step3: Apply the digest to the password that needs hashed.
     * Step4: Encode the hashed password
     *
     * Two ways to encode the password. Directly as commented (to hex string) OR using the base 64 encoder
     * @param passwordToHash
     * @param salt
     * @param algorithm
     * @return
     */
    private static String get_SHA_SecurePassword(String passwordToHash, byte[] salt, String algorithm) {
        String generatedPassword2 = null;String generatedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance(algorithm); //1
            md.update(salt); //2
            byte[] bytes = md.digest(passwordToHash.getBytes()); //3
//          BitwiseExamples.convertToHexString(bytes);

          /**
           * Base64 is used to transform the bytes to a printable format and storage if needed.
           * Using the encoder from the Java8 itself
           */
          generatedPassword = Base64.getEncoder().encodeToString(bytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;
    }

    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}