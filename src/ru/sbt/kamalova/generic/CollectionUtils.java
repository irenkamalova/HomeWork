package ru.sbt.kamalova.generic;

import java.util.*;

/**
 * Created by Irina Kamalova on 20.10.16.
 */
public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T t) {
        return source.indexOf(t);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        List<T> newList = new ArrayList<>();
        Iterator<? extends T> it = source.iterator();
        int counter = 0;
        while (counter < size && it.hasNext()) {
            newList.add(it.next());
            counter++;
        }
        return newList;
    }


    public static <T> void add(List<? super T> source, T t) {
        source.add(t);
    }


    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }



    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T l : c2) {
            if (c1.contains(l)) {
                return true;
            }
        }
        return false;
    }



    public static <T extends Comparable<T>> List<T> range(List<? extends T> list, T min, T max) {
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if ((t.compareTo(min) >= 0) && (t.compareTo(max) <= 0)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if ((comparator.compare(t, min) >= 0) && (comparator.compare(t, max) <= 0)) {
                newList.add(t);
            }
        }
        return newList;
    }

}

