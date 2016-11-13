package ru.sbt.kamalova.reflection;

/**
 * Created by Irina Kamalova on 13.11.16.
 */
public class Person {
    private String firstName;
    private String secondName;
    private int age;

    public Person() {
    }

    public Person(String firstName, String secondName, int age, boolean isMarried) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.isMarried = isMarried;
    }

    private boolean isMarried;


    private boolean isMarried() {
        return isMarried;
    }

    private void setMarried(boolean married) {
        isMarried = married;
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
