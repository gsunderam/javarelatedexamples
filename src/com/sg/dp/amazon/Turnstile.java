package com.sg.dp.amazon;

import static com.sg.dp.log.Logger.*;

import java.util.*;

/**
 * Time complexity: O(N) N = numCusts
 * Space : O(N) for the tree map to store N items
 */
public class Turnstile {
    public int[] custTimes(int numCusts, int[] times, int[] ways) {
        Map<Integer, Integer> timeMap = new TreeMap<>();
        int i = 0, j = 1; //Compare custs[i] and custs[j]. j is higher index
        int time = times[0]; //Keep track of what time index a customer is done

        int custIndex = -1; //will be i or j. Stores which cust was successful
        int prevDir = -1; //Track the previous direction used. 0 or 1 from ways []

        while (j < numCusts) {
//            stdout("Processing main cust " + i + " comp with " + j + " time: " + time);
            /** if time counter is lagging, advance it to times[i] */
            if (time < times[i]) time = times[i];

            /**
             * Resolve collision using the prev dir stored during the prev iteration, if ONLY the
             * prev iteration was done at the PREVIOUS second index. Otherwise allow the cust who is exiting (1)
             */
            if (isCollision(i, j, times, time)) {
//                stdout("collision..");
                if (time > 0 && times[j] == times[time - 1] + 1) prevDir = ways[custIndex];
                else prevDir = -1;

                if (prevDir != -1) { //prev second used
                    custIndex = ways[i] == prevDir ? i : j;
                } else { //Not used in prev second
                    custIndex = ways[i] == 1 ? i : j;
                }

                if (custIndex == i) { /** Reset i and j for next run. */
                    i = j;            /**  if j was done, just reset j to next index */
                    j++;
                } else j = custIndex + 1;
            } else { /** No collision, so just process lower index i, reset i & j */
                custIndex = i;
                i = j;
                j++;
            }

            /**
             * Use treemap to sort the values based on custIndex as the key. Value is the "time"
             * when the cust is let in
             */
            timeMap.put(custIndex, time);
            time++;
        }

        /** Last unprocessed custindex in the loop. if time cust arrived > time counter, use the former */
        timeMap.put(i, time < times[i] ? times[i] : time);
//        stdout(timeMap);
        return timeMap.values().stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * This occurs when 2 customers, one to enter and the other to exit, come at same second index
     * Two cases. (1) when times at i and j are some (2) when time counter equals timeAt j due to earlier
     * collisions.
     */
    public boolean isCollision(int i, int j, int[] times, int time) {
        return times[i] == times[j] || times[j] == time;
    }

    public static void main(String[] args) {
        Turnstile turnstile = new Turnstile();
        int numCusts = 4;
        int[] times = {0, 0, 1, 5};
        int[] ways = {0, 1, 1, 0};
        stdout(Arrays.toString(turnstile.custTimes(numCusts, times, ways)));
        numCusts = 5;
        int[] times2 = {0, 1, 1, 3, 3};
        int[] ways2 = {0, 1, 0, 0, 1};
        stdout(Arrays.toString(turnstile.custTimes(numCusts, times2, ways2)));

        numCusts = 4;
        int[] times3 = {0, 2, 2, 3};
        int[] ways3 = {1, 0, 1, 0};
        stdout(Arrays.toString(turnstile.custTimes(numCusts, times3, ways3)));

        numCusts = 6;
        int[] times4 = {0, 1, 1, 4, 5, 5};
        int[] ways4 = {1, 0, 1, 0, 1, 0};
        stdout(Arrays.toString(turnstile.custTimes(numCusts, times4, ways4)));
        int[] times5 = {0, 0, 1, 1, 5, 5};
        int[] ways5 = {0, 1, 1, 0, 1, 0};
        stdout(Arrays.toString(turnstile.custTimes(numCusts, times5, ways5)));
    }
}
