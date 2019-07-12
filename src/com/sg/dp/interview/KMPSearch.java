package com.sg.dp.interview;

import java.util.Arrays;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 7/8/2019.
 * KMP substring search:
 * Find the first occurence index of the pattern string in the input string. prints the index,
 * otherwise print -1. Efficient when input strings are large.
 *
 * time complexity: O(n + m)
 * space complexity: O(m)
 * where n = input string length and m = pattern length
 *
 * Traditional substr search time complexity: O(mn)
 */
public class KMPSearch {
    public static void main(String[] args) {
        String input = "abcdabeabclabsz";
        char[] inputChars = input.toCharArray();
        String pattern = "abclabs";
        char[] patternChars = pattern.toCharArray();
        int[] indices = formPrefixArray(pattern);
        stdout(Arrays.toString(indices));

        int j = 0;
        int index = -1;
        for(int i = 0; i < inputChars.length; i++) {
            if (inputChars[i] == patternChars[j]) {
                if (j == patternChars.length - 1) {
                    index = i - j;
                    j = 0;
                }

                j++;
            } else j = indices[j];
        }

        stdout("index: " + index);
        if (index != -1) {
            stdout(input.substring(index, index + patternChars.length));
        }
    }

    /**
     * This formastion is the crux of KMP substr search. Using this there is no need to go
     * backwards in the input string, thus cutting down time complexity!
     * @param pattern
     * @return
     */
    private static int[] formPrefixArray(String pattern) {
        int [] indices = new int[pattern.length()];
        char[] chars = pattern.toCharArray();
        int i = 0, j = 1;

        while (j <= pattern.length() - 1) {
            if (chars[i] == chars[j]) {
                indices[j] = i + 1;
                j++;i++;
            } else {
                if ( i > 0) i = indices[i - 1];
                else {
                    indices[j] = i;
                    j++;
                }
            }
        }

        return indices;
    }
}
