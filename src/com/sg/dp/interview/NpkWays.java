package com.sg.dp.interview;

import com.sg.dp.log.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 1/13/2018.
 * Find permutations 4 P 3. {123}, {124}, {212} etc
 * Given 4 elements Print 4p3
 */
public class NpkWays {
    private static final Integer [] numbers = {1,2,3,4,5};

    /**
     * 1) Take first element 1
     * 2) take sublist of remaning [234]
     * 3) Add 1 with each sublist's memebrs [12, 13, 14]
     * 4) From list in 3 above, add the remaing elements to each [123, 124, 132, 134, 142, 143]
     * 5) Repeat the process for ALL elements in the arrayj
     */
    public static List<List<Integer>> findNCK(int k) {
        List<Integer> numberList = Arrays.asList(numbers);
        List<List<Integer>> nodes = new ArrayList<>();
        List<List<Integer>> resultList = new ArrayList<>();

        for (int i : numberList) {
            List<Integer> temp = new ArrayList<>();
            temp.add(i);
            nodes.add(temp);
        }

        for (List<Integer> list : nodes) {
            for (int i : numberList) {
                List<Integer> temp = new ArrayList<>();
                temp.addAll(list);
                if (!temp.contains(i)) {
                    temp.add(i); //12
                    for (int j : numberList) {
                        List<Integer> temp2 = new ArrayList<>();
                        temp2.addAll(temp);
                        if (!temp2.contains(j)) {
                            temp2.add(j);
                            resultList.add(temp2);
                        }
                    }
                }
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        int noOfElements = 3;
        if (args[0] != null) {
            noOfElements = Integer.parseInt(args[0]);
            stdout("No of elements: " + noOfElements);
        }

        List<List<Integer>> nckList = findNCK(noOfElements);
        nckList.stream().forEach(Logger::stdout);
    }
}
