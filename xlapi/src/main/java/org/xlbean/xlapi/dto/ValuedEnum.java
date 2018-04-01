package org.xlbean.xlapi.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface ValuedEnum<T> {

    T getValue();

    @SuppressWarnings("unchecked")
    static <T extends Enum<T>> T valueOf(Class<T> paramClass, Object value) {
        ValuedEnum<?>[] enumValues = null;
        try {
            Method m = paramClass.getDeclaredMethod("values");
            enumValues = (ValuedEnum[]) m.invoke(null);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | ClassCastException e) {
            throw new IllegalArgumentException(paramClass.getCanonicalName() + " is not ValuedEnum class.");
        }

        for (ValuedEnum<?> obj : enumValues) {
            if (value == null) {
                if (obj.getValue() == null) {
                    return (T) obj;
                }
            } else if (value.equals(obj.getValue())) {
                return (T) obj;
            }
        }
        throw new IllegalArgumentException("No enum constant " + paramClass.getCanonicalName() + "." + value);
    }

}
