package com.sg.dp.interview;

import java.util.*;
import java.util.stream.Stream;

import static com.sg.dp.log.Logger.stdout;
import static com.sg.dp.log.Logger.print;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * Created by chandrashekar on 10/21/2016.
 * Programs typically asked in interviews and are generally useful in software development
 * for problem solving
 */
public class StringManipulations {
    public static void main(String[] args) {
        findWordsCount();
        findCommonElements();
        testFlatMap();
        findLongPalindrome();
        findUnionOfArrays();
        findFirstNonRepeatedLetter();
    }

    private static void findFirstNonRepeatedLetter() {
        String s = "stress"; //prints "t"
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>(); //to maintain order
        char [] chars = s.toCharArray();

        for(int i = 0; i < chars.length; i++) {
            map.put(chars[i], (map.containsKey(chars[i]) ? map.get(chars[i]) + 1 : 1));
        }

        map.entrySet().stream().filter(entry -> entry.getValue() == 1).limit(1).forEach(entry ->
                print("\n First Non repeated letter in the String " + s + " is \"" + entry.getKey() + "\""));
    }

    private static void findUnionOfArrays() {
        int a [] = {1,2,3,4,5,7,9,12,13};
        int b [] = {2,4,5,6,8,9,10,11,14,15};
        int i = 0; int j = 0;

        while(i < a.length && j < b.length) { //using && prevents index exception
            if (a[i] < b[j]) {
                print(a[i++] + "\t");
            } else if (a[i] > b[j]) {
                print(b[j++] + "\t ");
            } else {
                print(a[i++] + "\t");
                j++;
            }
        }

        /** Print remaining elements. Just for self documenting the actual use case, checking which array to iterate -:)*/
        if (i == a.length) { //If a were traversed before b
            while(j < b.length) print(b[j++] + "\t");
        } else if (j == b.length) { //Vice Versa
            while(i < a.length) print(a[i++] + "\t");
        }
    }

    /** Finds longest subsstring matching content like aba, ababa, abba etc. in the given string */
    private static void findLongPalindrome() {
        String s = "baxdzzababa";
        char[] charArray = s.toCharArray();
        int len = charArray.length;
        /** low tracks the left and high the right side of the center. maxlength is the length of the palindrome found thus far */
        int low = 0, high = 0, start = 0, maxlength = 0; //start saves the "low" value once a palindrome is found

        for(int i = 1; i < len; i++) {
            /** This is for ODD paindromes. Compares 0 and 2 with 1 as center and proceeds to len - 1 */
            low = i - 1; high = i + 1;

            while (low >= 0 && high < len && charArray[low] == charArray[high]) {
                if (high - low + 1 >= maxlength) {
                    start = low;
                    maxlength = high - low + 1;
                }

                low--; high++;
            }

            /** This is for EVEN paindromes. Compares 0 and 1, 1 and 2 etc. and proceeds to len */
            low = i - 1; high = i;
            while (low >= 0 && high < len && charArray[low] == charArray[high]) {
                if (high - low + 1 >= maxlength) {
                    start = low;
                    maxlength = high - low + 1;
                }

                low--; high++;
            }
        }

//        stdout("start: " + start + " length: " + maxlength);
        stdout("Longest palindrome in the String " + s + " is ");
        stdout(s.substring(start, start + maxlength));
    }

    /**
     * Illustrates the use of a flatMap in Java 8
     */
    public static void testFlatMap() {
        Stream.of(asList(1, 2), asList(3, 4)) // Stream of List<Integer>
                .flatMap(List::stream) //Flattens the list streams above in to one Stream i.e. [1,2], [3,4]
                .map(i -> i + 1)       //becomes [1,2,3,4]. map here processes this flattened list
                .forEach(i -> System.out.print(i + "\t"));
    }

    private static void findCommonElements() {
        final int a [] = {10, 50, 60, 100, 200, 400, 500, 800};
        final int b [] = {60, 70, 200, 500, 800, 1000};
        final int c [] = {30, 40, 60, 150, 200, 300, 500, 700, 800, 1200};

        Arrays.stream(c).flatMap(i -> Arrays.stream(a).filter(j -> i == j)).flatMap(k -> Arrays.stream(b).filter(m -> k == m)).
               forEach(System.out::println);
    }

    private static void findWordsCount() {
        String s = "Im for God and God men. That That is is a great place. Wonder Wonder. luck is always luck y and luck";
        final Map<String, Integer> words = new LinkedHashMap<String, Integer>(); //to maintain order

        List<Map<String, Integer>> wordsCountList = Arrays.stream(s.split("\\s")).map(str -> {
            words.put(str, (words.get(str) != null ? words.get(str) + 1 : 1));
            return words;
        }).collect(toList()); //Just to get ONLY the last element

        stdout(wordsCountList.get(wordsCountList.size() - 1));
    }
}