package ru.sbt.kamalova.reflection;

/**
 * Created by Irina Kamalova on 14.11.16.
 */
public class Child extends Person {
    private String firstName;
    private String secondName;
    private int age;

    public Child() {
    }

    public Child(String firstName1, String secondName1, int age1) {
        firstName = firstName1;
        secondName = secondName1;
        age = age1;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
