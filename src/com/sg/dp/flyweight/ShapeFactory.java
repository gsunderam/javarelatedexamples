package com.sg.dp.flyweight;

import java.util.HashMap;

/**
 * Created by chandrashekar on 10/18/2016.
 */
public class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap();

    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color); //get via the intrinsic state, the color

        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle); //store using the intrinsic state
            System.out.println("Creating circle of color : " + color);
        }

        return circle;
    }
}
