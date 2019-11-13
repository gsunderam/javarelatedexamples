package com.sg.dp.interview;

import java.util.Arrays;

import static com.sg.dp.log.Logger.stdout;

public class ArrayTeasers {
    private static final int [] NUMBERS = {17, 11, 11, 24, 14, 13, 13, 25, 25, 5, 18, 21, 24, 24, 25};

    public static void main(String[] args) {
        stdout(Arrays.toString(NUMBERS));
        printSecondBig();
        isArmstrongNumber(153);
        sortTwoArrays();
    }

    private static void isArmstrongNumber(int number) {
        int n = number, sum = 0;

        do {
            sum += (n % 10) * (n % 10) * (n % 10);
            n = n / 10;
        } while (n >= 1);

        if (sum == number) stdout("The number " + number + " is an Armstrong number");
        else stdout("The number " + number + " is NOT an Armstrong number");
    }

    /**
     * Time complexity O(n)
     */
    private static void printSecondBig() {
        int big = 0, sBig = 0;

        for (int number : NUMBERS) {
            if (number > big) {
                sBig = big;
                big = number;
            } else {
                if (number > sBig && number < big) sBig = number;
            }
        }

        stdout("Second largest is " + sBig);
    }

    private static void sortTwoArrays() {
        final int a [] = {17, 34, 11, 30};
        final int b [] = {15, 10, 7, 9, 23};
        final int c [] = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] > b[j])  insertSort(b[j++], c, k++);
            else insertSort(a[i++], c, k++);
        }

        while (i < a.length) insertSort(a[i++], c, k++);
        while (j < b.length) insertSort(b[j++], c, k++);

        stdout(Arrays.toString(c));
    }

    private static void insertSort(int n, int[] c, int k) {
        c[k] = n;
        if (k == 0) return;

        int index = k - 1;
        while (index >= 0 && c[index] > n) {
            c[index + 1] = c[index];
            index--;
        }

        c[index + 1] = n;
    }
}
