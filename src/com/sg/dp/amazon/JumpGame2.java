package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.Arrays;

import static com.sg.dp.log.Logger.stdout;

/**
 * Jump game 2 leet code question with Dynamic programming approach
 */
public class JumpGame2 {
    private static final int [] numbers = {3, 1, 3, 1, 1, 2, 4};

    public static void main(String[] args) {
        int result = minJumps();
        stdout("Result is " + result);
    }

    private static int minJumps() {
        int len = numbers.length;
        int [] dp = new int[len];

        for(int i = len - 2; i >= 0; i--) {
            if (numbers[i] == 0) {
                continue;
            }

            int min  = Integer.MAX_VALUE;
            for(int j = i + 1; j <= i + numbers[i] && j < len; j++) {
                min = Math.min(min, dp[j] + 1);
            }

            dp[i] = min;
        }

        stdout(Arrays.toString(dp));
        return dp[0];
    }
}
