package com.sg.dp.interview;

import com.sg.dp.log.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 2/19/2018.
 * Find permutations N P K.
 * Given N elements Print NpK where K is the number of permutations
 */
public class NCKGeneric {
    private static final List<List<Integer>> resultList = new ArrayList<>();

    private static int k;
    private static List<Integer> numbers;
    /**
     * 1) Take first element 1
     * 2) take sublist of remaning [234...]
     * 3) Add 1 with each sublist's memebrs [12, 13, 14]
     * 4) From list in 3 above, add the remaing elements to each [123, 124, 132, 134, 142, 143,....]
     * 5) Repeat the process for ALL elements in the array until k is reached
     */
    public static void findNCK(int k, Integer [] numbers) {
        NCKGeneric.k = k;
        NCKGeneric.numbers = Arrays.asList(numbers);;

        List<Integer> numberList = Arrays.asList(numbers);
        List<List<Integer>> nodes = new ArrayList<>();

        numberList.stream().forEach(i -> {
            List<Integer> temp = new ArrayList<>();
            temp.add(i);
            nodes.add(temp);
        });

        getPermutations(nodes);
    }

    public static void getPermutations(List<List<Integer>> nodes) {
        nodes.stream().forEach(list -> {
            numbers.stream().forEach(i -> {
                List<Integer> temp = new ArrayList<>();
                temp.addAll(list);
                if (!temp.contains(i)) {
                    temp.add(i);
                    if (temp.size() == k) {
                        resultList.add(temp);
                    } else {
                        List<List<Integer>> temp2 = new ArrayList<>();
                        temp2.add(temp);
                        getPermutations(temp2);
                    }
                }
            });
        });
    }

    public static void main(String[] args) {
        final Integer [] numbers = {1,2,3,4};
        int noOfPermutations = 3;
        if (args.length > 0 && args[0] != null) {
            noOfPermutations = Integer.parseInt(args[0]);
            stdout("No of elements: " + noOfPermutations);
        }

        findNCK(noOfPermutations, numbers);
        stdout("Total permutations for " + numbers.length + " taken " + noOfPermutations + " elements is: " + resultList.size());
        resultList.stream().forEach(Logger::stdout);
    }
}
