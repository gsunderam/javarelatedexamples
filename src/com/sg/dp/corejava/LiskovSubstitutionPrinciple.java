package com.sg.dp.corejava;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 10/31/2016.
 */
public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setLength(23);
        rectangle.setWidth(24);
        stdout("Area of rectangle " + rectangle.area()); //Fine for rectangle.

        rectangle = new Square(); //Point rectangle to a Square
        rectangle.setLength(23);
        rectangle.setWidth(24); //One could still set this height, though it doesn't make sense for the square
        /**
         So LSP says, Because superclass reference couldn't be unambiguosly substituted with that of the subclass,
         the Inheritance relationship between Rect and Square violates the LSP. There is ambiguity in the get/set width
         method as its NOT applicable for SQUARE but valid for a RECTANGLE. So, the area method returns an incorrect value
         for a supposed "rectangle"
         In the real world the idea is that the Client code shouldn't be concerned with whether a ref. is super or sub type.
         It should be able to seamlessly use either of these and yet, get consistent results for identical input(s).
         */
        stdout("Area of rectangle (as a Square) " + rectangle.area()); //Incorrect Area of rectangle.
        /**
         * To fix the above problem, create an abstract base class with area as only method. Have Rectangle and Square implement the
         * area method. In the real world issues with "is a" could be addressed using "has-a"
         */
    }
}

class Rectangle {
    int length;
    int width; //No meaning for a square

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    /**
     * This doesn't make sense for a Square
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    public int area() {
        return length * width;
    }

}

/**
 * This inheritance violates the LSP
 */
class Square extends Rectangle {
    public int area() {
        return length * length;
    }
}