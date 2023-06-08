package com.sg.dp.interview;

import com.sg.dp.log.Logger;

import java.util.*;
import java.util.stream.IntStream;

import static com.sg.dp.log.Logger.*;
import static java.lang.StrictMath.sqrt;

/**
 * Created by chandrashekar on 11/9/2016.
 */
public class ArrayPuzzlers {
    public static void main(String[] args) {
        printNonDuplicates(); //These two removes duplicates
        printNonDuplicatesNoSort();

        printDistinct(); //prints all distinct ones
        printLeaders();

        printPrimes(101);
        printNonDupesBetweenArrays();
        groupAnagrams("cat tac dog god sat tas");

        /** For GS interview prep. Compare with jump ganme and optimize */
        getCycleLength(new int[] {1, 2, 3, 4, 1}, 0);
        getCycleLength(new int[] {1, 2, 4, 4, 1}, 0);
        getCycleLength(new int[] {1, 2, 0}, 0);
        getCycleLength(new int[] {1, 2, 2, 4, 1}, 0);
    }

    private static void getCycleLength(int[] nums, int start) {
        int i = start, count = 1;
        int [] arr = new int[nums.length];
        Arrays.fill(arr, -1);
        arr[start] = 0;

        while(i < nums.length) {
            i = nums[i];

            if (i < nums.length) {
                if (arr[i] > -1) {
                    stdout(count - arr[i]);
                    break;
                }

                arr[i] = count++;
            }
        }

        if (i > nums.length - 1) stdout(-1);
    }

    private static void groupAnagrams(String s) {
        String [] words = s.split(" ");
        Arrays.sort(words, new AnagramComparator());
        stdout(Arrays.toString(words));
    }

    private static void printNonDupesBetweenArrays() {
        Set<String> list = new HashSet<>(Arrays.asList("1212", "2323", "3090", "2048", "2049", "356"));
        List<String> dupesList = Arrays.asList("2048", "2049", "356");
        List<String> dupesList2 = Arrays.asList("3090", "1212");
        list.removeAll(dupesList);
        list.removeAll(dupesList2);
        print(list);
    }

    private static void printPrimes(int n) {
        if (n < 2) return;

        stdout("Primes till " + n + " are ");
        printTab(2); //one knows 2 is prime. Then filter all even numbers upfront
        IntStream.rangeClosed(3, n).filter(i -> i % 2 != 0).forEach(i -> isPrime(i));
        print("\n");
    }

    private static void isPrime(int n) {
        long count = IntStream.rangeClosed(3, (int) sqrt(n)).filter(k -> n % k == 0).limit(1).count();
        if (count == 0) printTab(n);
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
        stdout("Time Taken for Sort Array approach: " + (System.nanoTime() - start) / 10000);
    }

    /**
     * This algorithm is around 6 times faster than the sort approach above! Need to see more later
     */
    private static void printNonDuplicatesNoSort() {
        int [] a = {13, 56, 56, 23, -19, 95, -19, 101, 13, -19};
        long start = System.nanoTime();
        List<Integer> dupes = new ArrayList<>();

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
        stdout("Time Taken for Non Sort Nested Loop scan: " + (System.nanoTime() - start) / 10000);
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
        stdout("Time taken to print va streams: " + printTime/10000);

        begin = System.nanoTime();
        for (int n : set) printTab(n);
        stdout("Time taken to print via For Loop: " + (System.nanoTime() - begin)/10000);

        stdout("\nTime Taken via Set to print distinct: " + (System.nanoTime() - start - printTime) / 10000);
    }

    /**
     * An element is leader if it is greater than all the elements to its right side. The rightmost element is always a leader.
     * REF: https://www.tuturself.com/posts/view?menuId=82&postId=943&src=linkedin
     */
    private static void printLeaders() {
        // Leaders are- 55 34 15 9 2 -> should be the output
        int a [] = {55, 10, 34, 5, 15, 9, 2};
        printTab("Leaders are " + a[a.length - 1]);

        int big = a[a.length - 1];
        for (int i = a.length - 1; i >= 1; i--) {
            if (a[i - 1] > big) {
                printTab(a[i - 1]);
                big = a[i -1];
            }
        }
        stdout("\n");
    }

    private static class AnagramComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return sortChars(a).compareTo(sortChars(b));
        }

        private String sortChars(String input) {
            char [] ch = input.toCharArray();
            Arrays.sort(ch);
            return new String(ch);
        }
    }
}
