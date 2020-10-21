package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.Arrays;

public class ArraySortFix2 {
    private static final int [] nums = {1, 2, 4, 7, 11, 12, 7, 6, 5, 16, 18, 19};

    public int [] sortIndices() {
        int leftIndex = getLeftIndex();
        int rightIndex = getRightIndex();

        return new int [] {leftIndex, rightIndex};
    }

    private int getLeftIndex() {
        int min = Integer.MAX_VALUE, index = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < min) {
                min = nums[i];
            } else index = i;
        }

        Logger.stdout(index);
        return index;
    }

    private int getRightIndex() {
        int max = Integer.MIN_VALUE, index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            } else index = i;
        }

        Logger.stdout("right " + index);
        return index;
    }

    public static void main(String[] args) {
        ArraySortFix2 arrFix = new ArraySortFix2();
        Logger.stdout(Arrays.toString(arrFix.sortIndices()));
    }
}
