package com.sg.dp.interview;

import static com.sg.dp.log.Logger.printTab;
import static com.sg.dp.log.Logger.stdout;

public class PrimeFactors {
    public static void main(String[] args) {
        int n = 1011;
        printPrimeFactors(n);
    }

    private static void printPrimeFactors(int n) {
        for(int i = 2; i <= n; i++) {
            while (n % i == 0) {
                printTab(i);
                n /= i;
            }
        }
    }
}
