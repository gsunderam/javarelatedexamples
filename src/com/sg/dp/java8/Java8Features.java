package com.sg.dp.java8;

import com.sg.dp.log.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.sg.dp.log.Logger.printTab;
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
        //call the static method as if it were a class
        stdout(Util.getNoOfProcessors());

        SeaPlane plane = new SeaPlane();
        plane.takeOff();plane.cruise();plane.turn();plane.land();
        optionalDemo();

        //Lambdas can make code reusable
        List<Integer> numbers = Arrays.asList(1,2, 3, 4, 5, 6, 7, 8, 9, 10,11);

        sumAll(numbers, x -> true); //Sum all numbers
        sumAll(numbers, x -> x % 2 == 0); //Sum all even numbers
        sumAll(numbers, x -> x % 3 == 0); //sum multiples of 3
        sumAll(numbers, x -> x % 3 == 0 && x % 2 != 0); //sum multiples of 3 only if x is odd
    }

    public static void optionalDemo() {
        String [] words = new String[10];
        Optional<String> checkNull = Optional.ofNullable(words[5]);

        if (checkNull.isPresent()) System.out.print(words[5].toLowerCase());
        else System.out.println("word is null");

        stdout(checkNull.orElse(new String("default"))); //orElse
        stdout(checkNull.orElseGet(() -> "Default Value")); //orElseGet

        //Optional with a value present
        Optional<String> name = Optional.of("SunderamG");
        stdout(name.isPresent() ? "name is " + name.get(): "name is null"); //using .get()
        name.ifPresent(Logger::stdout); //using lammbda style method references
    }

    /**
     * With predicate, this code can be used for several different conditions
     * @param numbers
     * @param p
     */
    public static void sumAll(List<Integer> numbers, Predicate<Integer> p) {
        int total = 0;

        for (int number : numbers) {
            if (p.test(number)) total += number;
        }

        printTab(total);
    }
}
