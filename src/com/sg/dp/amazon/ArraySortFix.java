package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.Arrays;

public class ArraySortFix {
    private static final int [] nums = {1, 2, 4, 7, 11, 12, 7, 6, 5, 16, 18, 19};

    public int [] sortIndices() {
        int maxLeft = getSortedLeft();
        int minright = getSortedRight();

        int i = getLeftIndex(maxLeft, minright);
        int j = getRightIndex(maxLeft, minright);
        return new int [] {i, j};
    }

    public int getSortedLeft() {
        int i = 0;
        while (i < nums.length && nums[i + 1] >= nums[i]) i++;
        Logger.stdout("max left " + i);
        return i;
    }

    public int getSortedRight() {
        int j = nums.length - 1;
        while (j >= 0 && nums[j - 1] < nums[j]) j--;
        Logger.stdout("min right " + j);
        return j;
    }

    public int getLeftIndex(int maxleft, int minright) {
        int minmiddle = getMinMiddle(maxleft, minright);
        int i = maxleft;
        while (nums[i--] >= minmiddle);
        Logger.stdout("left index " + (i+2));
        return i + 2;
    }

    public int getRightIndex(int maxleft, int minright) {
        int maxmiddle = getMaxMiddle(maxleft, minright);
        int j = minright;
        while (nums[j++] < maxmiddle);
        Logger.stdout("Right index " + (j - 2));
        return j - 2;
    }

    public int getMinMiddle(int maxleft, int minright) {
        int i = maxleft + 1, val = Integer.MAX_VALUE;
        while (i <= minright) {
            if (nums[i] < val) val = nums[i];
            i++;
        }

        Logger.stdout("Min middle " + val);
        return val;
    }

    public int getMaxMiddle(int maxleft, int minright) {
        int j = maxleft, val = Integer.MIN_VALUE;
        while (j <= minright - 1) {
            if (nums[j] > val) val = nums[j];
            j++;
        }

        Logger.stdout("Max Middle " + val);
        return val;
    }

    public static void main(String[] args) {
        ArraySortFix arrFix = new ArraySortFix();
        int[] result = arrFix.sortIndices();
        Logger.stdout(Arrays.toString(result));
    }

}
