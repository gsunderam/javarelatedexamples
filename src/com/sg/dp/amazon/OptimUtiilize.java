package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.*;

public class OptimUtiilize {
    public List<int[]> findOptimalIds(List<int[]> a, List<int[]> b, int target) {
        Collections.sort(a, Comparator.comparingInt(arr -> arr[1]));

        Collections.sort(b, Comparator.comparingInt(arr -> arr[1]));

        return getOptimalIds(a, b, target);
    }

    public List<int[]> getOptimalIds(List<int[]> a, List<int[]> b, int target) {
        var len = b.size() - 1;
        var j = len;
        Map<Integer, List<int[]>> totalsMap = new TreeMap<>(Collections.reverseOrder());

        for (int [] arr : a) {
            j = len;
            while (j >= 0) {
                var arrb = b.get(j);
                var total = arr[1] + arrb[1];
                if (total <= target) {
                    var valueList = totalsMap.getOrDefault(total, new ArrayList<>());
                    valueList.add(new int[]{arr[0], arrb[0]});
                    totalsMap.put(total, valueList);
                    break;
                }

                j--;
            }
        }

        var entries = totalsMap.entrySet();
        return !entries.isEmpty() ? entries.stream().findFirst().get().getValue() : Collections.emptyList();
    }

    public static void main(String[] args) {
        List<int[]> a = Arrays.asList(new int[]{4, 10}, new int[]{2, 5}, new int[]{3, 7}, new int[]{1, 3});
        List<int[]> b = Arrays.asList(new int[]{1, 2}, new int[]{4, 5}, new int[]{3, 4}, new int[]{2, 3});
        OptimUtiilize optim = new OptimUtiilize();
        List<int[]> result = optim.findOptimalIds(a, b, 10);
        printResults(result);
        a = Arrays.asList(new int[]{3, 14}, new int[]{1, 8}, new int[]{2, 7});
        b = Arrays.asList(new int[]{3, 14}, new int[]{1, 5}, new int[]{2, 10});
        printResults(optim.findOptimalIds(a, b, 20));
    }

    private static void printResults(List<int[]> result) {
        if (result.isEmpty()) {
            Logger.stdout(result);
            return;
        }

        result.forEach(arr -> Logger.printTab(Arrays.toString(arr)));
        Logger.print("\n");
    }
}
