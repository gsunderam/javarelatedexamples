package com.sg.dp.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chandrashekar on 1/13/2018.
 * Find permutations 4 P 3. {123}, {124}, {212} etc
 * Given 4 elements Print 4p3
 */
public class CombinationNCK {
    private static final Integer [] numbers = {1,2,3,4};

    public static void main(String[] args) {
        /**
         * 1) Take first element 1
         * 2) take sublist of remaning [234]
         * 3) Add 1 with each sublist's memebrs [12, 13, 14]
         * 4) From list in 3 above, add the remaing elements to each [123, 124, 132, 134, 142, 143]
         * 5) Repeat the process for ALL elements in the arrayj
         */
        List<Integer> numberList = Arrays.asList(numbers);
        for (int i = 0; i < numberList.size(); i++) {
            List<Integer> first = new ArrayList<>();
            first.add(i);
            List<Integer> sublist = formSubListExcept(first);
            List<List<Integer>> firstLevelLists = combine(i, sublist);
            for (List<Integer> intList : firstLevelLists) {
                List<Integer> nextLevelList = formSubListExcept(intList);
                //combine(intList, nextLevelList);
            }
        }
    }

    private static List<List<Integer>> combine(int i, List<Integer> sublist) {

        return null;
    }

    private static List<Integer> formSubListExcept(List<Integer> list) {

        return list;
    }
}
