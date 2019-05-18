package com.kamalova.string;

public class LocaleExample {

    public static void main(String[] args) {
        String s = "Jpoint";
        java.util.Locale.setDefault(new java.util.Locale("tr", "TR"));
        System.out.println(s.toUpperCase());
    }
}
