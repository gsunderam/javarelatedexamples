package com.sg.dp.interview;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 11/25/2018.
 */
public class MedianOfArrays {
    private static final int [] a = {1, 3, 5, 7, 12, 15, 17, 21, 23, 33, 45, 47, 48, 51, 54, 55, 100};
    private static final int [] b = {2, 4, 6, 8, 11, 14, 16, 24, 25, 29, 32, 46, 49, 53, 56, 59, 65};//, 44, 55, 66, 200};

    private static final int NUMBER_OF_ELEMENTS = a.length + b.length;
    private static final int MED_INDEX = NUMBER_OF_ELEMENTS / 2 + 1;

    public static void main(String[] args) {
        stdout("Median index: " + MED_INDEX);
        if (a.length == 0 && b.length == 0) {
            stdout("Arrays are empty. Please provide valid values");
            System.exit(0);
        }

        findMedianAuxArray();
        findMedianInPlace();
    }

    /**
     * This algorithm is faster for medium sized arrays by 3 times
     */
    private static void findMedianAuxArray() {
        int i = 0, j = 0, k = 0;
        long start = System.nanoTime();
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

//        stdout("i: " + i + " j: " + j);
        double median = determineMedian(c);
        long time_aux_array = System.nanoTime() - start;
        stdout("Time taken for Auxillary array: " + time_aux_array / 1000);
        stdout("Median is : " + median);
    }

    private static double determineMedian(int[] c) {
        return NUMBER_OF_ELEMENTS % 2 == 0 ? (double)(c[c.length - 1] + c[c.length - 2]) / 2 : c[c.length - 1];
    }

    /**
     * This is slower relative to auxillary array
     */
    private static void findMedianInPlace() {
        int i = 0, j = 0;
        long start = System.nanoTime();
        boolean a_flag = false;

        while(i + j + 2 <= MED_INDEX + 1) {
            if (i < a.length && j < b.length) {
                if (a[i] > b[j]) {
                    j++;
                    a_flag = false;
                } else {
                    i++;
                    a_flag = true;
                }
            } else if (i >= a.length) {
                j++;
                a_flag = false;
            } else if (j >= b.length) {
                i++;
                a_flag = true;
            }
        }

//        stdout("i: " + i + " j: " + j + " flag: " + a_flag);
        double median = determineMedian(i, j, a_flag);
        long time_inplace = System.nanoTime() - start;
        stdout("Time for In Place algorithm: " + time_inplace / 1000);
        stdout("Median is = " + median);
    }

    private static double determineMedian(int i, int j, boolean a_flag) {
        int max_value = a_flag ? a[i - 1] : b[j - 1];
//        stdout("max: " + max_value);
        int min_value = 0;
        if (NUMBER_OF_ELEMENTS % 2 == 0) {
            min_value = a_flag ? (i >=2 && j >= 1 ? Math.max(a[i - 2], b[j - 1]) : (j != 0 ? b[j - 1] : a[i - 2])) :
                    (j >= 2 && i >= 1 ? Math.max(b[j - 2], a[i - 1]) : (i != 0 ? a[i - 1] : b[j - 2]));
        }

//        stdout("min: " + min_value);
        double median = min_value != 0 ? (double) (max_value + min_value) / 2 : max_value;
        return median;
    }
}
