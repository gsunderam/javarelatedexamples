package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author CZ
 * @date 1/15/2022 9:06 PM
 */
public class FindDictWords {
    private static final String word = "klrpodewmttt";
    final static Set<String> dict = new HashSet<>();

    public static void main(String[] args) {
        formDict();
        Logger.stdout(dict);
        int len = word.length();
        for (int i = 0; i < len; i++) {
            StringBuilder subWord = new StringBuilder(word.charAt(i) + "");
            for(int j = i + 1; j < len; j++) {
                subWord.append(word.charAt(j));
                if (dict.contains(subWord.toString())) Logger.stdout(subWord.toString());
            }
        }
    }

    private static void formDict() {
        dict.add("klr");
        dict.add("pode");
        dict.add("ttt");
        dict.add("ewm");
        dict.add("pod");
        dict.add("mtt");
    }
}
