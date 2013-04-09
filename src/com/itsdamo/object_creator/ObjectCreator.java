package com.itsdamo.object_creator;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: dam5s
 * Date: 8/04/13
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public class ObjectCreator<T> {
    private T instance;

    public ObjectCreator(T instance) {
        this.instance = instance;
    }

    public ObjectCreator<T> with(String fieldName, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, fieldValue);

        return this;
    }

    public T get() {
        return instance;
    }
}
