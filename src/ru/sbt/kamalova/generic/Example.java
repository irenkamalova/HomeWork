package com.kamalova.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
What will be as a result of execution?
*/
public class Example {

    public static void main(String[] args) {
            ArrayList<Integer> ar = new ArrayList<>();
            List temp = ar;
            temp.add(new java.util.Date());
            temp.add(new Float(1.66));
            Iterator it = ar.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            System.out.println(ar.get(0));
    }
}
