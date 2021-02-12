package com.sg.dp.interview;

import com.sg.dp.log.Logger;

import java.util.Arrays;

/**
 * Brute force approach. Time : n * 2 pow 2n
 */
public class GenerateAllParens {
    public static void generateParens(char [] str, int pos) {
        if (pos == str.length) {
            if (isValid(str)) {
                Logger.stdout(new String(str));
            }
        } else {
            str[pos] = '('; // Add left and recurse
            generateParens(str, pos + 1);

            str[pos] = ')'; // Add right and recurse
            generateParens(str, pos + 1);
        }
    }

    public static boolean isValid(char [] str) {
        int left = 0, right = 0, maxleft = str.length / 2;
        for (char ch: str) {
            if (ch == '(') left++;
            else right++;

            if (right > left || left > maxleft) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        generateParens(new char[3 * 2], 0);
    }

}
