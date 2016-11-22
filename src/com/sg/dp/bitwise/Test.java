package com.sg.dp.bitwise;

import static com.sg.dp.log.Logger.print;
import static com.sg.dp.log.Logger.printTab;
import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 11/15/2016.
 */
public class Test {
    public static void main(String[] args) {
        /** 60 is stored as 00000000000000000000000000111100 - 32 bits.
         * All actions below use this representation
         **/
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        c = a & b;   /* 12 = 0000 1100 */
        stdout("a & b = " + c );

        c = a | b;   /* 61 = 0011 1101 */
        stdout("a | b = " + c );

        c = a ^ b;   /* 49 = 0011 0001 */
        stdout("a ^ b = " + c );

        c = ~a;      /*-61 = 1100 0011 */
        stdout("~a = " + c );

        c = a << 2; /* 240 = 0111 1000 */
        stdout("a << 2 = " + c );

        /** Preserves the sign bit */
        c = a >> 2;  /* 15 = 1111 */
        stdout("a >> 2  = " + c );

        c = a >>> 2; /* 15 = 0000 1111 */
        stdout("a >>> 2 = " + c ); //Fills zero in the left. so negative numbers loose their sign

        int n = -3;
        stdout("n >>> 2 = " + (n >>> 2));

        swap(a, b);
    }

    private static void swap(int a, int b) {
        printTab("a = " + a); printTab(" b = " + b);
        a = a + b; //swap without an intermediary variable
        b = a - b;
        a = a - b;
        print("\na = " + a + " b = " + b);

        a = a ^ b; //swap using bitwise operators
        b = a ^ b;
        a = b ^ a;
        print("\na = " + a + " b = " + b);
    }
}
