package com.sg.dp.interview;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 7/23/2018.
 * Given an unsorted array, say, numbers = [10, 4, 2, 6]. Print the Number of subsets from this array
 * that adds to 16. The above array as input should produce "2" as output because there are two subsets
 * viz. [10, 4, 2] and [10, 6] that add up to a total = 16. Assume the array can only contain +ve ints
 *
 * Without Memoization the Time complexity is (2 ^ n). so Memoizing this algorithm to get O(n) performance
 *
 * {@link Hashtable} seems to give better perf. than {@link HashMap}
 *
 */
public class SubsetsSum {
    HashMap<String, Integer> memo = new HashMap<>();

    public int countSubsets(int [] numbers, int total) {
        long begin = System.nanoTime();
        int count = countSets(numbers, total, 0);
        stdout("Time taken for alogorithm: " + (System.nanoTime() - begin));
        stdout(memo);
        return count;
    }

    private int countSets(int[] numbers, int total, int index) {
        String key = total + ":" + index;
        if (memo.get(key) != null) return memo.get(key);

       if (index == numbers.length - 1) {
           if (total == numbers[index] || total == 0) return 1;
           else return 0;
       }

       if (total == 0) return 1;
       else if (total < 0) return 0;

        int count = countSets(numbers, total - numbers[index], index + 1) + countSets(numbers, total, index + 1);
        memo.put(key, count);

        return count;
    }

    public static void main(String[] args) {
        stdout(new SubsetsSum().countSubsets(new int [] {10, 6, 2, 4, 14}, 16));
    }
}
