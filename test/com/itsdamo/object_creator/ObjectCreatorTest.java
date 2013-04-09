package com.itsdamo.object_creator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: dam5s
 * Date: 8/04/13
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class ObjectCreatorTest {

    private class DomainModel {
        private int id;
        private Object object;

        public int getId() {
            return id;
        }

        public Object getObject() {
            return object;
        }
    }

    private DomainModel instanceToUpdate;
    private ObjectCreator<DomainModel> objectCreator;
    private DomainModel updatedInstance;

    @Before
    public void setUp() {
        instanceToUpdate = new DomainModel();
        objectCreator = new ObjectCreator<DomainModel>(instanceToUpdate);
    }

    @Test
    public void get_shouldReturnTheInstance() {
        updatedInstance = objectCreator.get();
        assertEquals(instanceToUpdate, updatedInstance);
    }

    @Test
    public void with_shouldSetAPrivateObjectProperty() throws Exception {
        Object expectedObject = "Hello!";
        updatedInstance = objectCreator.with("object", expectedObject).get();
        assertEquals(expectedObject, updatedInstance.getObject());
    }

    @Test
    public void with_shouldSetAPrivateIntProperty() throws Exception {
        updatedInstance = objectCreator.with("id", 32).get();
        assertEquals(32, updatedInstance.getId());
    }
}
