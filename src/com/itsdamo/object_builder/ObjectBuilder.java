package com.itsdamo.object_builder;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: dam5s
 * Date: 8/04/13
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public class ObjectBuilder<T> {
    private T instance;

    public ObjectBuilder(T instance) {
        this.instance = instance;
    }

    public ObjectBuilder<T> with(String fieldName, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = null;
        Class objectClass = instance.getClass();

        while (field == null) {
            try {
                field = objectClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                objectClass = objectClass.getSuperclass();
                if (objectClass == null) throw e;
            }
        }

        field.setAccessible(true);
        field.set(instance, fieldValue);

        return this;
    }

    public T build() {
        return instance;
    }
}
