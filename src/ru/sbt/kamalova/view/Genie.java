package com.kamalova.image;


import java.time.Year;

/*
What will be as a result?
*/
public class Genie {
    public static final Genie ONE = new Genie();

    private static final int C = Year.now().getValue();
    private static final int Y = 0;
    private final int age;
    private Genie() {
        System.out.println("C is " + C);
        age = C - Y;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        System.out.println(ONE.getAge());
        System.out.println(Year.now().getValue());
    }
}
