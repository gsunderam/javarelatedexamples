package com.sg.dp.corejava;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 11/1/2016.
 *
 * Refer the link: http://stackoverflow.com/questions/4250062/what-is-the-difference-between-and-a-and-z-in-regex
 */
public class RegexExamples {
    public static void main(String[] args) {
        final Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s']+$");
        final Pattern safePattern = Pattern.compile("\\A[a-zA-Z0-9\\s]+\\Z");

        String city = "This is gilbert arizona 756";
        String cityWithNewline = "This is\ngilbert arizona 756\n<script>alert('gotcha')</script>\n";

        //TODO: Understand the difference between the two. Have examples to illustrate the same.
        //..//A and //z are preferred over ^ and $
        Matcher matcher = pattern.matcher(city);
        stdout("City matches ? " + (matcher.find() ? matcher.group() : matcher.find()));

        Matcher matcher1 = pattern.matcher(cityWithNewline);
        stdout("City Newline matches ? " + (matcher1.find() ? matcher1.group() : matcher1.find()));

        Matcher matcher2 = safePattern.matcher(cityWithNewline);
        stdout("City Newline  safePattern matches ? " + (matcher2.find() ? matcher2.group() : matcher2.find()));

        //Arrays.stream(pattern.split(cityWithNewline)).forEach(System.out::println);
    }
}
