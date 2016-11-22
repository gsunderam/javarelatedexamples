package com.sg.dp.bitwise;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 11/15/2016.
 * Program to demonstrate some bitwise masks
 */
public class Masks {
    public static void main(String[] args) {
        byte a = 8;
        byte b = 10;
        stdout("a = " + Integer.toString(a, 2) + " b = " + Integer.toString(b, 2));

        //define masks
        byte mask0 = 1; //0001
        byte mask1 = 2; //0010
        byte mask2 = 4; //0100
        byte mask3 = 8; //1000

        stdout("Turn Bit 0 ON " + Integer.toString(a | mask0, 2)); //set bits
        stdout("Turn Bit 1 ON " + Integer.toString(a | mask1, 2));
        stdout("Turn Bit 2 ON " + Integer.toString(a | mask2, 2));
        stdout("Turn Bit 3 ON " + Integer.toString(a | mask3, 2));

        stdout("Clear Bit 1 OFF " + Integer.toString(b & ~mask1, 2)); //clear bits

        stdout("Toggle Bit 1 " + Integer.toString(b ^ mask2, 2)); //clear bits
    }
}
