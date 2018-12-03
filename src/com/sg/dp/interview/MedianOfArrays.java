package com.sg.dp.interview;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 11/25/2018.
 */
public class MedianOfArrays {
    private static final int [] a = {2, 7, 11, 14, 25, 34};
    private static final int [] b = {1, 4, 5, 6, 12, 15, 35};//, 44, 55, 66, 200};

    private static final int NUMBER_OF_ELEMENTS = a.length + b.length;
    private static final int MED_INDEX = NUMBER_OF_ELEMENTS / 2 + 1;

    public static void main(String[] args) {
        stdout("Median index: " + MED_INDEX);
        findMedianAuxArray();
        findMedianInPlace();
    }

    private static void findMedianAuxArray() {
        int i = 0, j = 0, k = 0;
        int [] c = new int[MED_INDEX];

        while(k <= MED_INDEX - 1) {
            if (i < a.length && j < b.length) {
                if (a[i] > b[j]) c[k++] = b[j++];
                else c[k++] = a[i++];
            } else if (i >= a.length) {
                c[k++] = b[j++];
            } else if (j >= b.length) {
                c[k++] = a[i++];
            }
        }

        stdout("i: " + i + " j: " + j);
        stdout("Median is : " + determineMedian(c));
    }

    private static double determineMedian(int[] c) {
        return NUMBER_OF_ELEMENTS % 2 == 0 ? (double)(c[c.length - 1] + c[c.length - 2]) / 2 : c[c.length - 1];
    }

    private static void findMedianInPlace() {
        int i = 0, j = 0;
        boolean a_flag = false;

        while(i + j + 2 <= MED_INDEX + 1) {
            if (a[i] > b[j]) {
                j++; a_flag = false;
            } else {
                i++; a_flag = true;
            }
        }

//        stdout("i: " + i + " j: " + j);
        stdout("Median is = " + determineMedian(i, j, a_flag));
//        stdout("Median is : " + determineMedian(i, j));
    }

    private static double determineMedian(int i, int j, boolean a_flag) {
        int max_value = a_flag ? a[i - 1] : b[j - 1];
//        stdout("max: " + max_value);
        int min_value = 0;
        if (NUMBER_OF_ELEMENTS % 2 == 0) {
            min_value = a_flag ? Math.max(a[i - 2], b[j - 1]) : Math.max(b[j - 2], a[i - 1]);
        }

//        stdout("min: " + min_value);
        return min_value != 0 ? (double) (max_value + min_value) / 2 : max_value;
    }
}
