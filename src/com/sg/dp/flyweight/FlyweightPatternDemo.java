package com.sg.dp.flyweight;

/**
 * Created by chandrashekar on 10/18/2016.
 *
 * Basically only 5 objects are created though 20 circles are drawn. Therefore memory foot print and GC overhead is
 * slightly reduced. This is a great pattern to reduce the mem foot print. Must use in the next project!
 */
public class FlyweightPatternDemo {
    private static final String colors[] = { "Red", "Green", "Blue", "White", "Black" };
    public static void main(String[] args) {

        for(int i=0; i < 20; ++i) {
            //Reuse from the flyweight if color is existing already
            Circle circle = (Circle)ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX()); //Then set the extrinsic states in lieu of creating objects
            circle.setY(getRandomY());
            circle.setRadius(100);

            circle.draw();
        }
    }
    private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
    }
    private static int getRandomX() {
        return (int)(Math.random()*100 );
    }
    private static int getRandomY() {
        return (int)(Math.random()*100);
    }
}
