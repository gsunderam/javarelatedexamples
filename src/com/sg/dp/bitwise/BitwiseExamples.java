package com.sg.dp.bitwise;

import static com.sg.dp.log.Logger.print;
import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 11/2/2016.
 *
 * Bitwise operators could be used for various nifty little things in application development. Just
 * created a placeholder class as a playground for those.
 */
public class BitwiseExamples {
    private static final char[] symbols="0123456789ABCDEF".toCharArray();

    public static void main(String[] args) {
        int a = 5 ^ 6;
        stdout(a);
        a = 2 ^ 3;
        stdout(a);
        String passwd = "Indiatrip15";
        char [] hexValues = convertToHexString(passwd.getBytes());

        for (char ch : hexValues) print(ch);
        print("\n");

        stdout("ASCII value is " + convertHexToString(hexValues));

        //Using a more straight forward example
        stdout(asciiToHex(passwd));
        stdout(hexToASCII(asciiToHex(passwd)));
    }

    public static char[] convertToHexString(byte[] array) {
        char[] hexValue = new char[array.length * 2];

        for(int i=0;i<array.length;i++) {
            //convert the byte to an int
            int current = array[i] & 0xff;
            //determine the Hex symbol for the last 4 bits
            hexValue[i * 2 + 1] = symbols[current & 0x0f];
            //determine the Hex symbol for the first 4 bits
            hexValue[i * 2] = symbols[current >> 4];
        }

        return hexValue;
    }

    public static String convertHexToString(char [] hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for(int i = 0; i < hex.length; i += 2) {
            //grab the hex in pairs
            String output = hex[i] + "" + hex[i + 1];
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            char dec = (char) decimal;
            sb.append(dec);
            print(dec);

            temp.append(decimal);
        }

        stdout("\nDecimal : " + temp.toString());

        return sb.toString();
    }

    private static String convertToHexStringV1(byte[] bytes) {
        String generatedPassword2;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    private static String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }

    private static String hexToASCII(String hexValue) {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < hexValue.length(); i += 2) //Hex strings are encoded as 2 bytes per regular base 10 byte
        {
            String str = hexValue.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }
}
