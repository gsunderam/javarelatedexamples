package com.sg.dp.corejava;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 10/26/2016.
 */
public class SystemUtils {
    private static final String NEW_LINE = System.getProperty("line.separator");

    /**
     * SystemUtils executable function.
     *
     * @param arguments Command-line arguments; none expected.
     */
    public static void main(final String[] arguments) {
        showIdentityHashcodes();
    }

    private static void showIdentityHashcodes() {
        final Integer int1 = 1;
        final int int2 = 1;
        final Long long1 = 1L;
        final long long2 = 1L;
        final String str = "SomeString";
        final String nullStr = null;
        SimpleData simpleData = new SimpleData("AnotherString");

        printHashCodes(int1);
        printHashCodes(int2);
        printHashCodes(long1);
        printHashCodes(long2);
        printHashCodes(str);
        printHashCodes(nullStr);
        printHashCodes(simpleData); //same for both if not overridden
        double d = .666666666666666; //input
        double obj = ((double) Math.round(d * 10000)) / 100; //convert to double
        stdout(obj);
    }

    /**
     * Print the overridden and identity hash codes of the provided object.
     *
     * @param object Object whose overridden and identity hash codes are to be
     *    printed to standard output.
     */
    public static void printHashCodes(final Object object) {
        stdout(NEW_LINE + "====== " + String.valueOf(object) + "/" + (object != null ? object.getClass().getName() : "null")
                + " ======");
        stdout("Overridden hashCode: " + (object != null ? object.hashCode() : "N/A"));
        stdout("Identity hashCode: " + System.identityHashCode(object));
    }

    private static class SimpleData {
        String data;

        public SimpleData(String data) {
            this.data = data;
        }

//        @Override
//        public int hashCode() {
//            return 43;
//        }
    }
}
