package com.sg.dp.flyweight;

/**
 * Created by chandrashekar on 10/18/2016.
 */
public class Circle implements Shape {
    private String color; //Intrinsic state
    private int x; //These 3 below are extrinsic state
    private int y;
    private int radius;

    public Circle(String color){
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }
}
