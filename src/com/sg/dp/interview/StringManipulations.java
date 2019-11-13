package com.sg.dp.interview;

import com.sg.dp.log.Logger;

import java.util.*;
import java.util.stream.Stream;

import static com.sg.dp.log.Logger.*;
import static java.util.Arrays.asList;
import static java.util.Arrays.toString;
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
        findUnionOfArrays();
        findFirstNonRepeatedLetter();
        findFirstNonRepeatedLetterV2();
        isPalindrome("adbda");
        reverseString("johndoeu");
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

    /**
     * Illustrates the use of a flatMap in Java 8
     */
    public static void testFlatMap() {
        Stream.of(asList(1, 2), asList(3, 4)) // Stream of List<Integer>
                .flatMap(List::stream) //Flattens the list streams above in to one Stream i.e. [1,2], [3,4]
                .map(i -> i + 1)       //becomes [1,2,3,4]. map here processes this flattened list
                .forEach(i -> System.out.print(i + "\t"));

        Map<String, List<String>> people = new HashMap<>();
        people.put("John", Arrays.asList("555-1123", "555-3389"));
        people.put("Mary", Arrays.asList("555-2243", "555-5264"));
        people.put("Steve", Arrays.asList("555-6654", "555-3242"));

        people.values().stream().flatMap(Collection::stream).forEach(Logger::printTab);
        newline();
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
        final Map<String, Integer> words = new HashMap<String, Integer>();

        List<Map<String, Integer>> wordsCountList = Arrays.stream(s.split("\\s")).map(str -> {
            words.put(str, (words.get(str) != null ? words.get(str) + 1 : 1));
            return words;
        }).collect(toList()); //Just to get ONLY the last element

        stdout(wordsCountList.get(wordsCountList.size() - 1));
    }

    /** Just uses hashmap */
    private static void findFirstNonRepeatedLetterV2() {
        String s = "stress"; //prints "t"
        Map<Character, Integer> map = new HashMap<Character, Integer>(); //to maintain order
        char [] chars = s.toCharArray();

        for(char ch : chars) {
            map.put(ch, (map.containsKey(ch) ? map.get(ch) + 1 : 1));
        }

        for (char ch : chars) {
            if (map.get(ch) == 1) {
                stdout("\n First Non repeated letter in the String " + s + " is \"" + ch + "\"");
                break;
            }
        }
    }

    public static boolean isPalindrome(String str) {
        for(int i = 0, j = str.length() -1; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                stdout("String " + str + " is NOT a palindrome");
                return false;
            }
        }

        stdout("String " + str + " is a palindrome");
        return true;
    }

    private static void reverseString(String str) {
        long start = System.nanoTime();
        char[] chars = str.toCharArray();
        char temp = 'c';

        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        stdout("Time taken for swapping: " + (System.nanoTime() - start));

        printTab("Reverse of " + str + " is ");
        print(new String(chars));
    }
}