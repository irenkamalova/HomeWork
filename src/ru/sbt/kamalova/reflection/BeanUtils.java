package ru.sbt.kamalova.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Irina Kamalova on 13.11.16.
 */
public class BeanUtils {

    /**
     *      Scans object "from" for all getters. If object "to"
     *      contains correspondent setter, it will invoke it
     *      to set property value for "to" which equals to the property
     *      of "from".
     *      <p/>
     *      The type in setter should be compatible to the value returned
     *      by getter (if not, no invocation performed).
     *      Compatible means that parameter type in setter should
     *      be the same or be superclass of the return type of the getter.
     *      <p/>
     *      The method takes care only about public methods.
     *      
     * @param to   Object which properties will be set.
     *
     * @param from Object which properties will be used to get values.
     *      
     */

    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Method[] MethodsTo = from.getClass().getMethods();
        List<Method> getters = new ArrayList<>();
        
        for (Method m : MethodsTo) {
            if (m.getName().startsWith("get")) {
                getters.add(m);
            }
        }
        
        Method[] MethodsFrom = to.getClass().getMethods();
        for (Method setter : MethodsFrom) {
            if (setter.getName().startsWith("set")) {
                String s = setter.getName().substring(3);
                for (Method getter : getters) {
                    if (getter.getName().substring(3).equals(s)) {
                        Class<?>[] classes = setter.getParameterTypes();
                        Class getterClass = getter.getReturnType();
                        if (getterClass.isAssignableFrom(classes[0])) {
                            Object o = getter.invoke(from);
                            setter.invoke(to, getter.invoke(from));
                        }
                    }
                }
            }
        }
    }
}
