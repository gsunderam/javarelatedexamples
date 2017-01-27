package com.sg.dp.java8;

import com.sg.dp.log.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.sg.dp.log.Logger.stdout;
import static java.util.stream.Collectors.*;

/**
 * Created by chandrashekar on 1/26/2017.
 */
public class CollectionsExamples {
    public  static List<Person> createPeople() {
        List<Person> people = new ArrayList<Person>();
        people.add(0, new Person("Ram", 5));
        people.add(1, new Person("Krishna", 45));
        people.add(2, new Person("Govind", 35));
        people.add(3, new Person("Ramya", 35));
        people.add(4, new Person("Vidya", 5));
        people.add(5, new Person("Priya", 17));
        people.add(6, new Person("Muruga", 15));
        people.add(6, new Person("Kroger", 45));

        return people;
    }

    private static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String toString() {
            return name + " " + age;
        }
    }

    public static void main(String[] args) {
        List<Person> people = createPeople();

        //Comparing accepts a function and the chaining call "thenComparing" is a DEFAULT method!
        people.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getName))
                .forEach(Logger::stdout);

        //group by age
        stdout(people.stream().collect(groupingBy(Person::getAge, mapping(Person::getName, toList()))));
    }
}
