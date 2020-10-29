package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.sg.dp.log.Logger.*;

public class ClosedChamber {
    public static void main(String[] args) {
        ClosedChamber inventory = new ClosedChamber();
        int [] start = {1, 0, 3, 6};
        int [] end = {4, 6, 11, 15};
        String items = "*|**|*|****|**";
        Integer [] result = inventory.findClosedItems(items, start, end);
        stdout("Result is " + Arrays.toString(result));
    }

    public Integer[] findClosedItems(String items, int [] start, int [] end) {
        if (items == null || items.length() < 2) return null;
        if (!validate(items, start, end)) return null;

        var index = items.indexOf("|");
        var pipeIndices = new ArrayList<Integer>();

        while (index != -1) {
            pipeIndices.add(index);
            index = items.indexOf("|", index + 1);
        }

        stdout(pipeIndices);
        return findNumItems(pipeIndices, start, end);
    }

    public Integer[] findNumItems(List<Integer> indices, int [] start, int [] end) {
        var resultList = new ArrayList<Integer>();

        for (var i = 0; i <= start.length - 1; i++) {
            resultList.add(getResult(indices, start[i], end[i]));
        }

        return resultList.toArray(new Integer[resultList.size()]);
    }

    public boolean validate(String items, int [] start, int [] end) {
        return Arrays.stream(start).filter(i -> i > items.length()).limit(1).count() == 0
                || Arrays.stream(end).filter(i -> i > items.length()).limit(1).count() == 0;
    }

    public int getResult(List<Integer> indices, int start, int end) {
        int i = 0, result = 0;
        while (start > indices.get(i)) i++;

        int j = indices.size() - 1;
        while (end < indices.get(j)) j--;

        for (int k = i; k + 1 <= j; k++) {
            result += indices.get(k + 1) - indices.get(k) - 1;
        }

        return result;
    }
}
