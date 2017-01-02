package com.sg.dp.interview;

import com.sg.dp.log.Logger;

import java.util.*;

import static com.sg.dp.log.Logger.*;

/**
 * Created by chandrashekar on 11/9/2016.
 */
public class ArrayPuzzlers {
    public static void main(String[] args) {
        printNonDuplicates(); //These two removes duplicates
        printNonDuplicatesNoSort();

        printDistinct(); //prints all distinct ones
        printLeaders();
    }

    /**
     * This approach is slower than the non sort alogorithm. Surprising!
     * Need to test this in another machine in office soon.
     **/
    private static void printNonDuplicates() {
        int [] a = {13, 56, 56, 23, -19, 95, -19, 101, 13, -19};
        long start = System.nanoTime();
        Arrays.sort(a);
        int temp = 0;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != a[i + 1] && a[i] != temp) {
                 print(a[i] + "\t");

                //Print the last element
                if (i + 1 == a.length -1) print(a[i + 1]);
            } else temp = a[i];
        }

        print("\n");
        stdout("Time Taken for Sort Array approach: " + (System.nanoTime() - start));
    }

    /**
     * This algorithm is around 6 times faster than the sort approach above! Need to see more later
     */
    private static void printNonDuplicatesNoSort() {
        int [] a = {13, 56, 56, 23, -19, 95, -19, 101, 13, -19};
        long start = System.nanoTime();
        List<Integer> dupes = new ArrayList<Integer>();

        for (int i = 0; i < a.length; i++) {
            if (!dupes.contains(a[i])) {
                for (int j = i + 1; j < a.length; j++) {
                    if (a[i] == a[j]) {
                        dupes.add(a[i]);
                        break;
                    }
                }
            }
        }

        for (int n: a) if (!dupes.contains(n)) printTab(n);
        print("\n");
        stdout("Time Taken for Non Sort Nested Loop scan: " + (System.nanoTime() - start));
    }

    /**
     * WARN: Just use for loop for printing. Streams are taking a lot of time to print stuff, relative to for loops
     * Need to do performance tuning in the next project with streams and regular stuff
     */
    private static void printDistinct() {
        int [] a = {13, 56, 56, 23, -19, 95, -19, 101, 13, -19};
        long start = System.nanoTime();

        Set<Integer> set = new HashSet<Integer>();
        for (int n : a) set.add(n);

        long begin = System.nanoTime();
        set.stream().forEach(Logger::printTab);
        long printTime = System.nanoTime() - begin;
        stdout("Time taken to print va streams: " + printTime);

        begin = System.nanoTime();
        for (int n : set) printTab(n);
        stdout("Time taken to print via For Loop: " + (System.nanoTime() - begin));

        stdout("\nTime Taken via Set to print distinct: " + (System.nanoTime() - start - printTime));
    }

    /**
     * An element is leader if it is greater than all the elements to its right side. The rightmost element is always a leader.
     * REF: https://www.tuturself.com/posts/view?menuId=82&postId=943&src=linkedin
     */
    private static void printLeaders() {
        // Leaders are- 55 34 15 9 2 -> should be the output
        int a [] = {55, 10, 34, 5, 15, 9, 2};
        printTab(a[a.length - 1]);

        int big = a[a.length - 1];
        for (int i = a.length - 1; i >= 1; i--) {
            if (a[i - 1] > big) {
                printTab(a[i - 1]);
                big = a[i -1];
            }
        }
    }
}
