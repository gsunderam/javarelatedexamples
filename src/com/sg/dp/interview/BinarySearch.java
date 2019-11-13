package com.sg.dp.interview;

import java.util.Arrays;

import static com.sg.dp.log.Logger.stdout;

public class BinarySearch {
    private static final int [] numbers = {23, 2, 10, 2, 3, 21, 7, 15, 23};

    public static void main(String[] args) {
        Arrays.sort(numbers);

        doBinarySearch(23);
        doBinarySearch(32);
        doBinarySearch(1);
        doBinarySearch(15);

        doBinarySearchIterative(23);
        doBinarySearchIterative(7);
        doBinarySearchIterative(45);

    }

    private static void doBinarySearchIterative(int n) {
        int index = searchIterative(0, numbers.length - 1, n);
        stdout("The number " + n + " (Iterative) is at index: " + index);
    }

    private static int searchIterative(int start, int end, int n) {
        while (start <= end) {
            int middle = (start + end) / 2;

            if (n == numbers[middle]) return middle;
            else if (n > numbers[middle]) {
                start = middle + 1;
            } else if (n < numbers[middle]) {
                end = middle - 1;
            }
        }

        return -1;
    }

    private static void doBinarySearch(int n) {
        int index = search(0, numbers.length -1, n);
        stdout("Number " + n + " is at index: " + index);
    }

    private static int search(int start, int end, int n) {
        if (start <= end) {
            int middle = (start + end) / 2;

            if (n == numbers[middle])  return middle;
            else if (n > numbers[middle])  return search(middle + 1, end, n);
            else if (n < numbers[middle])  return search(start, middle - 1, n);
        }

        return -1;
    }
}
