package com.sg.dp.corejava;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 11/26/2016.
 */
public class StringTests {
    public static void main(String[] args) {
        String test = "Hello";
        modifyString(test);
        stdout("Outside method, test: " + test); //prints "Hello" only
        
        //use stringbuilder 
        StringBuilder str = new StringBuilder("Hello");
        modify(str);
        stdout("String outside " + str);
    }

    /**
     * With string builder, the change is reflected inside as well as outside as expected.
     * This is "pass by reference" example. Strings are unique and a separate topic
     * @param str
     */
    private static void modify(StringBuilder str) {
        str.append("world");
        stdout("String inside " + str);
    }

    /**
     * Even though string ref is changed inside, strings are immutable. Java creates a new string
     * inside this method. This object is totally different from the object in main method
     * @param test
     */
    private static void modifyString(String test) {
        test += " world";
        stdout("Inside method, test: " + test); //Hello world
    }
}
