package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.HashMap;
import java.util.Map;

public class Denominations {
    private static final int [] denoms = {10, 5, 1};

    public int numWays(int total) {
        Map<String, Integer> map = new HashMap<>();
        int result = countWays(total, 0, map);
        Logger.stdout(map);
        return result;
    }

    public int countWays(int total, int index, Map<String, Integer> map) {
        String key = total + ":" + index;
        if (map.get(key) != null) {
            Logger.stdout("cached");
            return map.get(key);
        }

        int coin = denoms[index];
        if (index == denoms.length - 1) {
            int rem = total % coin;
            return rem == 0 ? 1 : 0;
        }

        int count = 0,  amt = 0;
        while (amt <= total) {
            count += countWays(total - amt, index + 1, map);
            amt += coin;
        }

        map.put(key, count);
//        Logger.stdout("amount " + amt + " " + total + ":" + index + " = " + count);

        return count;
    }

    public static void main(String[] args) {
        Denominations denoms = new Denominations();
        int result = denoms.numWays(16);
        Logger.stdout(result);
    }
}
