package com.sg.dp.interview;

import static com.sg.dp.log.Logger.*;

import java.util.Arrays;
import java.util.List;

/**
 * Find k ways to combine n elements. N c K math problem
 * Time Complexity : O(n square - n.k) or N! / k! (n-k)!
 */
public class NckWays {
    private static final String [] elements = {"4", "5", "6", "7"};
    private List<String> numbers = Arrays.asList(elements);
    private static final int k = 3;

    public int numWays() {
        return findWays("", numbers);
    }

    public int findWays(String prefix, List<String> list) {
        if (prefix.length() == k) {
            stdout(prefix);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < list.size(); i++) { 
            count += findWays(prefix + list.get(i), getSubList(list, i + 1, list.size()));
        }

        return count;
    }

    public List<String> getSubList(List<String> list, int index, int end) {
        List<String> strings = list.subList(index, end);
        return strings;
    }

    public static void main(String[] args) {
        NckWays nck = new NckWays();
        stdout("Answer: " + nck.numWays());
    }
}
