package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

/**
 * @author CZ
 * @date 4/21/2021 10:35 PM
 */
public class ArrMaxSum {
    public int maxSum(int [] numbers) {
        int rsum = 0, sum = Integer.MIN_VALUE, result = Integer.MIN_VALUE;

        for (var i = 0; i < numbers.length; i++) {
            rsum += numbers[i];
            if (i > 0) sum = numbers[i - 1] + numbers[i];

            if (sum > rsum) rsum = sum;
            if (rsum > result) result = rsum;
        }

        return result;
    }

    public static void main(String[] args) {
        ArrMaxSum arrMaxSum = new ArrMaxSum();
        int[] numbers = {2, 3, -5, -10, 6, -3, -1, 1, 7};
        int answer = arrMaxSum.maxSum(numbers);
        Logger.stdout("Answer is " + answer);
        int[] numbers1 = {2, -8, 1, -2, 4, -10};
        answer = arrMaxSum.maxSum(numbers1);
        Logger.stdout("Answer is " + answer);
        answer = arrMaxSum.maxSumOptimized(numbers1);
        Logger.stdout("Answer is " + answer);
    }

    /**
     * This one is from the Crack the coding interview book and HAS A BUG!
     * Doesn't work in all cases, as shown in the example above. The bug is
     * it doesn't carry the correct max in case of negatives.
     * @param numbers
     * @return
     */
    public int maxSumOptimized(int [] numbers) {
        int rsum = 0, maxSum = 0;

        for (var i = 0; i < numbers.length; i++) {
            rsum += numbers[i];
            if (rsum > maxSum) maxSum = rsum;
            else if (rsum < 0) rsum = 0;
        }

        return maxSum;
    }
}
