package com.sg.dp.interview;

import static com.sg.dp.log.Logger.stdout;

public class DecimalRepresentation {
    public static void main(String[] args) {
        String converted = fractionRepresentation(1, 3);
        converted = fractionRepresentation(6, 11);
        fractionRepresentation(1, 2);
    }

    private static String fractionRepresentation(int num, int den) {
        double d = (double) num / (double) den;
        String decimalNumber = String.valueOf(d);

        String intPart = decimalNumber.substring(0, decimalNumber.indexOf(".") + 1);
        String decPart = decimalNumber.substring(decimalNumber.indexOf(".") + 1, decimalNumber.length());

        int i = 0;
        String buffer = "",  fraction = "", result = decimalNumber;
        while (i < decPart.length()) {
            String temp = decPart.substring(0, i + 1);
            int end = temp.length() + i + 1;
            if (end < decPart.length()) {
                buffer = decPart.substring(i + 1, end);
            }

            if (temp.equalsIgnoreCase(buffer)) {
                fraction = temp;
                break;
            }

            i++;
        }

        if (fraction.length() > 0) result = intPart + "(" + fraction + ")";
        stdout("Answer is " + result);
        return result;
    }
}
