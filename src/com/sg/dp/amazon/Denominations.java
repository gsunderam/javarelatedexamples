package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Good example to illustrate dynamic programming. Caching intermediate results in a suitable
 * data structure like arrays, maps etc. This is memoization
 */
public class Denominations {
    private static final int [] denoms = {25, 10, 5, 1};

    public int numWays(int total) {
        /** memoizer map */
        int [][] map = new int [total + 1][denoms.length];
        int result = countWays(total, 0, map);
//        Arrays.stream(map).forEach(row -> Logger.stdout(Arrays.toString(row)));
        return result;
    }

    /** store results in a 2D int [] as we need to refer by "total" first followed by "index" */
    public int countWays(int total, int index, int[][] map) {
        if (map[total][index] != 0) {
            Logger.stdout("cached result for " + total + ":" + index);
            return map[total][index];
        }

        int coin = denoms[index];
        if (index == denoms.length - 1) {
            int rem = total % coin;
            if (rem == 0) map[total][index] = 1;
            return rem == 0 ? 1 : 0;
        }

        int count = 0,  amt = 0;
        while (amt <= total) { //recursion
            count += countWays(total - amt, index + 1, map);
            amt += coin;
        }

        map[total][index] = count; //DP
//        Logger.stdout("amount " + amt + " " + total + ":" + index + " = " + count);

        return count;
    }

    public static void main(String[] args) {
        Denominations denoms = new Denominations();
        int result = denoms.numWays(101);
        Logger.stdout(result);
    }
}
