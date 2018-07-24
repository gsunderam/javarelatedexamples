package com.sg.dp.interview;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 7/16/2018.
 * Dynamic programming to show recursion and memoization or caching. Below the result of intermediate calls are cached
 * to improve performance. Without caching n = 50 takes a long long time. But with memoization, function returns in a flash!
 */
public class FibonnaciSeries {
    private static long [] memo; //Array used to cache the results

    /**
     * Time complexity: O(2 ^ n) without caching
     * Time complexity: O(n) with caching - huge improvement.
     * @param n
     * @return
     */
    public long fibonnaci(int n) {
        if (n <= 0) {
            usage();
            return -1;
        }

        long result = 0;

        if (memo[n] != 0) return memo[n]; //Here the cached result is returned so that...

        if (n == 1 || n == 2) result = 1;
        else {
            result = fibonnaci(n - 1) + fibonnaci(n - 2); //...these calls don't need to be executed again.
        }

        memo[n] = result; //cache the intermediary results
        return result;
    }

    /**
     * Time Complexity O(n). Prefer this for large input without caching
     * @param n
     * @return
     */
    public long fibIterative(int n) {
        /** The first two terms of the sequence are 1, 1 */
        int sum1 = 1, sum2 = 1, sum = 0;

        for(int i = 3; i <= n; i++) {
            sum = sum1 + sum2;
            sum1 = sum2;
            sum2 = sum;
        }

        return sum;
    }

    private static void usage() {
        stdout("Please enter a number greater than 2");
    }

    public static void main(String[] args) {
        int n = 40;
        memo = new long[n + 1];
        stdout(new FibonnaciSeries().fibonnaci(n));
        stdout(new FibonnaciSeries().fibIterative(n));
//        stdout(Arrays.toString(memo));
    }
}
