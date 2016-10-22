package ru.sbt.kamalova.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Irina Kamalova on 20.10.16.
 */
public class CountMapImpl<T> implements CountMap<T> {

    private final Map<T, Integer> internalMap = new HashMap<>();

    @Override
    public void add(T t) {
        if (internalMap.containsKey(t)) {
            internalMap.put(t, internalMap.get(t) + 1);
        } else {
            internalMap.put(t, 1);
        }
    }

    @Override
    public int getCount(T t) {
        Integer count = internalMap.get(t);
        return count == null ? 0 : count;
    }

    @Override
    public int remove(T t) {
        Integer result = internalMap.remove(t);
        return result == null ? 0 : result;
    }

    @Override
    public int size() {
        return internalMap.size();
    }

    @Override
    public void addAll(CountMap<? extends T> source) {

        for (Map.Entry<? extends T, Integer> m : source.toMap().entrySet()) {
            if (internalMap.containsKey(m.getKey())) {
                internalMap.put(m.getKey(), m.getValue() + internalMap.get(m.getKey()));
            } else {
                internalMap.put(m.getKey(), m.getValue());
            }
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(internalMap);
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        destination.putAll(internalMap);
    }
}
