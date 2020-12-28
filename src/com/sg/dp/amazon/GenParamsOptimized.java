package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

public class GenParamsOptimized {
    public static void generateParens(char [] str, int left, int right, int pos) {
        if (left < 0 || right < left) return;

        if (left == 0 && right == 0) {
            Logger.stdout(new String(str));
        } else {
            str[pos] = '('; // Add left and recurse
            generateParens(str, left - 1, right, pos + 1);

            str[pos] = ')'; // Add right and recurse
            generateParens(str, left, right - 1, pos + 1);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        generateParens(new char[n * 2], n, n, 0);
    }
}
