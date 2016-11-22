package com.sg.dp.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static com.sg.dp.log.Logger.print;
import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 11/2/2016.
 *
 * REFERENCE: http://stackoverflow.com/questions/1205135/how-to-encrypt-string-in-java
 * This approach basically XOR's the message with a salt and encodes the result
 * To get the result back, decode the data and XOR the decoded data with the salt
 *
 * data = origMsg
 * encodedMsg = encode(xorMsg(origMsg, salt))
 * origMsg = xorMsg(decode(encodedMsg), salt)
 *
 * Here is the encryption mechanism is a simple XOR'ing of the secret data with a Key
 */
public class EncryptUtils {
    public static final String DEFAULT_ENCODING = "UTF-8";

    public static String base64encode(String text) {
        try {
            return Base64.getEncoder().encodeToString(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }//base64encode

    public static String base64decode(String text) {
        try {
            return new String(Base64.getDecoder().decode(text), DEFAULT_ENCODING);
        } catch (IOException e) {
            return null;
        }
    }//base64decode

    public static void main(String[] args) {
        String txt = "SecretData";
        String key = "javaone";
        stdout(txt + " XOR-ed to: " + (txt = xorMessage(txt, key)));

        String encoded = base64encode(txt);
        stdout(" is encoded to: " + encoded + " and that is decoding to: " + (txt = base64decode(encoded)));
        print("XOR-ing back to original: " + xorMessage(txt, key));
    }

    public static String xorMessage(String message, String key) {
        try {
            if (message == null || key == null) return null;

            char[] keys = key.toCharArray();
            char[] mesg = message.toCharArray();

            int ml = mesg.length;
            int kl = keys.length;
            char[] newmsg = new char[ml];

            for (int i = 0; i < ml; i++) {
                newmsg[i] = (char)(mesg[i] ^ keys[i % kl]);
            }//for i

            return new String(newmsg);
        } catch (Exception e) {
            return null;
        }
    }//xorMessage
}//class
