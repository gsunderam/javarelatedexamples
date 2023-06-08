package com.sg.dp.interview;

import com.sg.dp.log.Logger;

import java.util.Arrays;

import static com.sg.dp.log.Logger.*;
import static java.lang.String.format;

public class StringTeasers {

    public static final String PALIN = "bccbccbad";
    public static final String INPUT = "abcdefgh";
    public static final String ROTATION = "habcdefg";

    public static void main(String[] args) {
        stdout("Input string is " + INPUT);
        boolean isRotation = isRotation(INPUT, ROTATION);
        stdout(ROTATION.toUpperCase() + " is rotation of " + INPUT.toUpperCase() + "? = " + isRotation);

        drawPyramidPattern(5);

        printLongPalindrome(PALIN);
        printLongPalinOptimized("adbcbcb");
        printLongPalinOptimized("adbcbbcb");
        printLongPalinOptimized("bcbbcbaxcbcbc");

        printLongestString2Chars("aabbccddc");
        printLongestString2Chars("abaabc");
        printLongestString2Chars("abaabccbcb");
        printLongestString2Chars("ababcbdccdd");
        printLongestString2Chars("abbabdbdbdb");
        printLongestString2Chars("aaaaa");
        printLongestString2Chars("abbabdbdbef");

        findAnagrams("AABA", "AABABAA");
        findAnagrams("ABCD", "CBACDBBDCA");
        findAnagrams("TUBG", "CBACDBBDCA");

        findLongestUniqueString("reallylongstringsuccess");
        binaryReverse("13");
    }

    /**
     * draws pyramid pattern for a given level
     * @param n No of levels
     */
    private static void drawPyramidPattern(int n) {
        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= n - i; j++)  print(" ");

            for(int j = 1; j <= i; j++)  print("* ");
            print("\n");
        }
    }

    private static boolean isRotation(String input, String rotation) {
        if (input.length() != rotation.length()) return false;
        if (input.equalsIgnoreCase(rotation)) return true;

        String inputConcat = input + input;
        return inputConcat.contains(rotation);
    }

    /**
     * Brute force approach. Time complexity = O(n^3).
     * n^2 for outer loops * n to check if its palindrome
     * @param palin
     */
    private static void printLongPalindrome(String palin) {
        int length = palin.length() - 1; int maxLength = 0;
        String result = "";

        for(int i = 0; i < length - 1; i++) {
            StringBuilder word = new StringBuilder("" + palin.charAt(i));
            for (int j = i + 1; j <= length; j++) {
                word.append(palin.charAt(j));
//                stdout("String: " + word);
                if (isPalindrome(word.toString())) {
//                    stdout("Word " + word + " is palindrome");
                    if (word.length() > maxLength) {
                        maxLength = word.length();
                        result = word.toString();
                    }
                }
            }
        }

        stdout(PALIN + ": Longest palindrome is " + result);
    }

    private static boolean isPalindrome(String word) {
        if (word.length() == 1) return false;

        for (int i = 0, j = word.length() - 1; i <= j; i++,j--) {
            if (word.charAt(i) != word.charAt(j)) return false;
        }

        return true;
    }

    /**
     * Time complexity : O(n^2).
     * viz. (n - 2) times looping * n to check if the string palindrome.
     * @param palin
     */
    private static void printLongPalinOptimized(String palin) {
        int length = palin.length() - 1; int low = 0, high = 0;
        int maxlen = 0; int minIndex = 0, maxIndex = 0;

        for (int i = 1; i < length; i++) {
            low = i - 1; high = i + 1; //for odd palins like aba, axaxa etc.
            while (low >= 0 && high <= length && palin.charAt(low) == palin.charAt(high)) {
                low--;high++;
            }

            if (high - 1 - low > maxlen) {
                maxlen = high - low - 1;
                maxIndex = high - 1; minIndex = low + 1;
            }

            low = i ; high = i + 1; //for even palins like abba, caccac etc.
            while (low >= 0 && high <= length && palin.charAt(low) == palin.charAt(high)) {
                low--;high++;
            }

            if (high - 1 - low > maxlen) {
                maxlen = high - low - 1;
                maxIndex = high - 1; minIndex = low + 1;
            }
        }

        stdout("high: " + maxIndex + " low: " + minIndex);
        stdout(palin + ": Longest palindrome " +  palin.substring(minIndex, minIndex + maxlen));
    }

    private static void printLongestString2Chars(String input) {
        stdout("Input is: " + input);
        int i = 0, j = 1;
        int begin = 0, end = 0, max = 0;
        char ch2 = input.charAt(j);

        while(j < input.length()) {
            char ch1 = input.charAt(i);
            while (j < input.length() && input.charAt(i) == input.charAt(j))  j++;

            if (j < input.length()) ch2 = input.charAt(j++);

            while (j < input.length() && (ch2 == input.charAt(j) || ch1 == input.charAt(j))) j++;
            stdout(String.format("i = %d, j = %d", i, j));
            if (ch1 != ch2) {
                if (j - i > max) {
                    max = j - i;
                    begin = i;
                    end = j;
                }
            }

            /** This is to reset i. Could be done better for accuracy. But, this is concise and clear */
            if (j < input.length()) {
                for (i = j - 1; input.charAt(i) == ch2; i--);
                i += 1;
            }
        }

        stdout(input.substring(begin, end));
    }

    /**
     * Find anagrams in the given string. used sliding window technique. calculate ascii value of
     * the Anagram and compare it to the ascii value window text (0 to 3) and so on
     * Reference: https://www.geeksforgeeks.org/anagram-substring-search-search-permutations/
     * @param pattern Anagram string
     * @param text Input text
     */
    public static void findAnagrams(String pattern, String text) {
        stdout(format("Inputs pattern %s text %s", pattern, text));
        int patLen = pattern.length();
        int txtLen = text.length();
        int patValue = 0;

        patValue = getCharsValue(pattern, 0, patLen - 1);

        /** Sliding window will be = (0. 3), (1,4) etc. */
        for (int i = 0; i + patLen <= txtLen; i++) {
            //logic here. Get ascii value and compare with patValue
            int value = getCharsValue(text, i, i + patLen - 1);
            if (value == patValue) stdout(format("Found anagram at index: %d", i));
        }
    }

    private static int getCharsValue(String charArr, int start, int end) {
        int asciiValue = 0;

        for (int i = start; i <= end; i++) asciiValue += charArr.charAt(i);
        return asciiValue;
    }

    /** Convert the number to binary. Reverse it. Print the decimal value of reversed binary
     * String
     **/
    private static void binaryReverse(String decStr) {
        int num = Integer.parseInt(decStr);
        StringBuilder s = new StringBuilder();
        int sum = 0;

        while (num > 0) {
            s.append(num % 2);
            num /= 2;
        }

        stdout(s);
        int length = s.toString().toCharArray().length;
        for (int i = 0; i < length; i++) {
            int binBit = Integer.parseInt("" + s.charAt(i));
            sum += binBit * (Math.pow(2, length - 1 - i));
        }

        stdout(sum);
    }

    private static void findLongestUniqueString(String input) {
        int [] alphas = new int[256];
        char [] chars = input.toCharArray();
        Arrays.fill(alphas, -1);

        int i = 0, prev = 0, max = Integer.MIN_VALUE;
        int [] result = new int[2];
        for(char ch : chars) {
            if (alphas[ch] > -1) {
                prev = alphas[ch] + 1 > prev ? alphas[ch] + 1 : prev;
            }

           if (i - prev > max) {
               result[0] = prev;
               result[1] = i;
               max = i - prev;
           }

           alphas[ch] = i;
           i++;
        }

        stdout(input.substring(result[0], result[1] + 1));
    }
}
