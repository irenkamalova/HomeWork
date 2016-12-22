package ru.sbt.kamalova.java8;

import ru.sbt.kamalova.reflection.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Irina Kamalova on 22.12.16.
 */
public class Main {

    public static void main(String[] args) {
        List<Person> someCollection = new ArrayList<>();
        someCollection.add(new Person("Alice", "Smith", 5, true));
        someCollection.add(new Person("Jhon", "Smith", 35, true));
        Map<String, Person> m = Streams.of(someCollection)
        .filter(p -> p.getAge() > 20).transform(p -> new Person(p.getFirstName(), p.getAge() + 30))
        .toMap(Person::getFirstName, p -> p);

        for (Person p : m.values()) {
            System.out.println(p.getFirstName() + " " + p.getAge());
        }


    }
}
