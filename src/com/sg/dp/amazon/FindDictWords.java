package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CZ
 * @date 1/15/2022 9:06 PM
 */
public class FindDictWords {
    private static final String word = "klrpodewmttt";
    final static Map<String, Boolean> dict = new HashMap<>();

    public static void main(String[] args) {
        formDict();
        Logger.stdout(dict);
        int len = word.length();
        for (int i = 0; i < len; i++) {
            StringBuilder subWord = new StringBuilder(word.charAt(i) + "");
            for(int j = i + 1; j < len; j++) {
                subWord.append(word.charAt(j));
                if (dict.get(subWord.toString()) != null) Logger.stdout(subWord.toString());
            }
        }
    }

    private static void formDict() {
        dict.put("klr", true);
        dict.put("pode", true);
        dict.put("ttt", true);
        dict.put("ewm", true);
        dict.put("pod", true);
        dict.put("mtt", true);
    }
}
