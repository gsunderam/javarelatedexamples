package com.sg.dp.amazon;

import java.util.Arrays;

import static com.sg.dp.log.Logger.stdout;

/**
 * Dynamic programming with memoization. Also in CCI book
 * @Sunderam G
 * June 2 2023
 */
public class Staircase {
    public static void main(String[] args) {
        int steps = 15;
        int [] memo = new int[steps + 1];
        int count = countSteps(steps, memo);
        stdout(count);
        stdout(Arrays.toString(memo));
    }

    private static int countSteps(int rem, int[] memo) {
        int count = 0;
        if (rem == 0) return 1;
        else if (rem < 0) return 0;
        else if (memo[rem] != 0) return memo[rem];
        else {
             count = countSteps(rem - 1, memo) + countSteps(rem - 2, memo) + countSteps(rem - 3, memo);
             memo[rem] = count;
             return count;
        }
    }
}
