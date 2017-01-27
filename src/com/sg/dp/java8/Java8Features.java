package com.sg.dp.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 1/26/2017.
 * Java 8 talk by Venkat S. Java One
 */

/**
 * In java 8 interface can have static methods
 */
interface Util {
    public static int getNoOfProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }
}

/**
 * Interface can have default impls. This is to fix the diamond problem in C++
 */
interface Fly {
    default public void takeOff() { stdout("TakeOff");}
    default public void turn() { stdout("Turning");}
    default public void cruise() { stdout("cruising");}
    default public void land() { stdout("landing");}
}

//Can override a method in the base interface
interface FastFly extends Fly {
    default void takeOff() { stdout("FastFly::takeoff");}
}

interface Sail {
    default public void cruise() { stdout("Sail::cruise");}
}

class Vehicle {
    public void land() { stdout("Vehicle::land"); }
}

//1. Gets all base default impls out of the box
//2. children can override default methods
//3. If there is an impl in a class ANYWHERE in the heirarchy, then that gets precedence over any default impls in a CLOSEST
//   interface
//4. If there is a conflict in method selection from the parent interfaces, compiler errors! So implement that
//   method in the class and CALL the desired method as shown below. Note the FastFly.super...call. super is needed
//   to clarify the fact that a method is called as opposed to a static method in the same interface

class SeaPlane extends Vehicle implements FastFly, Sail {
    public void cruise() {
        stdout("SeaPlane::cruise");
        FastFly.super.cruise();
    }
}

public class Java8Features {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ram", "Krishna", "govinda", "Narayana");

        //Joining is another nifty utility to join items using a delimiter such as String
        stdout(names.stream().map(String::toUpperCase).collect(Collectors.joining(", ")));
        stdout(Util.getNoOfProcessors());
        SeaPlane plane = new SeaPlane();
        plane.takeOff();plane.cruise();plane.turn();plane.land();
    }
}
