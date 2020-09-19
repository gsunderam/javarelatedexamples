package com.sg.dp.amazon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.sg.dp.log.Logger.stdout;

public class StringCumArray {
    public static void main(String[] args) {
        areUniqueStrings();
    }

    private static void areUniqueStrings() {
        String [] testStrings = {"CHEER", "mckenzy", "Waltzdnce", "success", "", null};
        int k = 0;

        List<Boolean> results = Arrays.stream(testStrings).map(StringCumArray::isUnique).collect(Collectors.toList());
        for (int i = 0; i < testStrings.length; i++)
            stdout(String.format("String %s Result for unique = %s", testStrings[i], results.get(k++)));
    }

    public static boolean isUnique(String word) {
        if (word == null || word.length() == 0) return false;

        char[] chars = word.toCharArray();
        char [] temp = new char[chars.length];
        int k = 0;

        for (char ch: chars) {
            if (isCharPresent(ch, temp)) return false;
            temp[k++] = ch;
        }

        return true;
    }

    private static boolean isCharPresent(char ch, char[] temp) {
        for (char c: temp) {
            if (c == ch) return true;
        }

        return false;
    }
}
