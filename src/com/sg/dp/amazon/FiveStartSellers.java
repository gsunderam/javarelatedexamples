package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Time complexity : O(log N * count) ; N = size of ratings liat
 * space: O(N) for the queue
 */
public class FiveStartSellers {
    public int getFiveStarRatings(List<List<Integer>> productRatings, int ratingsThreshold) {
        Queue<List<Integer>> queue = new PriorityQueue<>(this::returnMaxDiff);

        double cr = productRatings.stream().mapToDouble(list -> {
            queue.offer(list);
            return ((double) list.get(0)) / list.get(1);
        }).average().getAsDouble();

        double currentRating = getCurrThreshold(cr);
        Logger.stdout("Current: " + currentRating);

        double result = currentRating;
        int count = 0;
        //count * logN times
        while(result <= ratingsThreshold) {
            count++;
            List<Integer> headRating = queue.poll();
            Logger.stdout(headRating);
            double newRating = ((double)(headRating.get(0) + 1)) / (headRating.get(1) + 1);
            double oldrating = ((double) headRating.get(0)) / (headRating.get(1));
            cr += (newRating - oldrating) / productRatings.size();
            result = getCurrThreshold(cr);
            Logger.stdout(result);
            queue.offer(Arrays.asList(headRating.get(0) + 1, headRating.get(1) + 1));
        }

        Logger.stdout("Final Total is " + result);
        return count;
    }

    /**
     * comparator for the Priority Queue
     * @param listA
     * @param listB
     * @return
     */
    public int returnMaxDiff(List<Integer> listA, List<Integer> listB) {
        double first = getDiff(listA);
        double second = getDiff(listB);
        double diff = second - first;

        if (diff < 0) return -1;
        else return 1;
    }

    public double getDiff(List<Integer> list) {
        return ((double) list.get(0) + 1) / (list.get(1) + 1) -
                ((double)list.get(0)) / (list.get(1));
    }

    /**
     * Get the threshold percent as double with 2 dec places
     * @param cr 0.6666 etc
     * @return 66.67
     */
    public double getCurrThreshold(double cr) {
        return  ((double) Math.round(cr * 10000)) / 100;
    }

    public static void main(String[] args) {
        FiveStartSellers sellers = new FiveStartSellers();
        List<List<Integer>> prs = new ArrayList<>();
        prs.add(Arrays.asList(4,4));
        prs.add(Arrays.asList(1,2));
        prs.add(Arrays.asList(3,6));
        int result = sellers.getFiveStarRatings(prs, 77);
        Logger.stdout("ANSWER: " + result);
    }

}
