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
        printSubsets(getSubsets(Arrays.asList(1, 5, 9, 11)));
        printNonDupesBetweenArrays();
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

    private static <T> List<List<? extends T>> getSubsets(List<? extends T> numbers) {
        if (numbers.isEmpty()) {
            //Needed to correctly combine the lists in the concat list method
            List<List<? extends T>> list = new ArrayList<>();
            list.add(Collections.emptyList());
            return list;
        }

        T first = numbers.get(0);
        List<? extends T> sublist = numbers.subList(1, numbers.size());

        /**
         * Get the subsets till the last but one first element and the last empty element to
         * complete the recursion
         **/
        List<List<? extends T>> rest = getSubsets(sublist);

        /** Pack the first element with the rest of the lists IN the sublists and return
         * all the concatted lists in a parent/top-level list
         **/
        List<List<? extends T>> concatLists = concat(first, rest);
        return insertAll(rest , concatLists);
    }

    private static <T> List<List<? extends T>> insertAll(List<List<? extends T>> rest, List<List<? extends T>> result) {
        List<List<? extends T>> finalResultList = new ArrayList<>();
        finalResultList.addAll(rest);
        finalResultList.addAll(result);

        return finalResultList;
    }

    /**
     *
     * @param first First element 11, 9 so on
     * @param sublist empty list INSIDE a List. needed for tis program to work correctly
     * @param <T> Integer or lower
     * @return combined list
     */
    private static <T> List<List<? extends T>> concat(T first, List<List<? extends T>> sublist) {
        List<List<? extends T>> concatLists = new ArrayList<>();

        for (List<? extends T> list : sublist) {
            List<T> copyList = new ArrayList<>();
            copyList.add(0, first);
            copyList.addAll(list);
            concatLists.add(copyList);
        }

        return concatLists;
    }

    private static <T> void printSubsets(List<List<? extends T>> subsets) {
        stdout("Subsets of [1, 5, 9, 11] are ");
        subsets.stream().forEach(Logger::stdout);
    }

//    private static void isPrimePreJava8(int n) {
//        boolean primeFlag = true;
//
//        for (int k = 3; k <= sqrt(n); k++) {
//            if (n % k == 0) {
//                primeFlag = false;
//                break;
//            }
//        }
//
//        if (primeFlag) printTab(n);
//    }
}
