package com.sg.dp.utils;

import static com.sg.dp.log.Logger.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenTests {

    public static void main(String[] args) {
        int [] nums1 = {2, 3, 4, 5};
        int nums2 [] = {8, 9, 10, 11};
        stdout(Arrays.toString(nums1));
        stdout(Arrays.toString(nums2));

        swap(nums1, nums2);

        stdout(Arrays.toString(nums1));
        stdout(Arrays.toString(nums2));

        Integer a = 1, b = 2;
//        swap(a, b);
        int temp = a;
        a = b;
        b = temp;
        stdout(a + ", " + b);

        String line = "10.0.0.1 - log entry 1 11";
        String IPADDRESS_PATTERN =
                "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        IPADDRESS_PATTERN = "((?:\\d+\\.){3}\\d+)";

        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            stdout(matcher.group());
        } else{
            stdout("0.0.0.0");
        }
    }
    private static void swap(int [] nums1, int [] nums2) {
        int [] temp = new int [nums1.length];
        System.arraycopy(nums1, 0, temp, 0, nums1.length);
        nums1 = new int[nums2.length];
        System.arraycopy(nums2, 0, nums1, 0, nums2.length);
        nums2 = new int [temp.length];
        System.arraycopy(temp, 0, nums2, 0, temp.length);
    }

    private static void swap(Integer a, Integer b) {
        int temp = a;
        a = b;
        b = temp;
    }
}
