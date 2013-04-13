package com.itsdamo.object_builder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: dam5s
 * Date: 8/04/13
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class ObjectBuilderTest {

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
    private ObjectBuilder<DomainModel> objectBuilder;
    private DomainModel updatedInstance;

    @Before
    public void setUp() {
        instanceToUpdate = new DomainModel();
        objectBuilder = new ObjectBuilder<DomainModel>(instanceToUpdate);
    }

    @Test
    public void get_shouldReturnTheInstance() {
        updatedInstance = objectBuilder.get();
        assertEquals(instanceToUpdate, updatedInstance);
    }

    @Test
    public void with_shouldSetAPrivateObjectProperty() throws Exception {
        Object expectedObject = "Hello!";
        updatedInstance = objectBuilder.with("object", expectedObject).get();
        assertEquals(expectedObject, updatedInstance.getObject());
    }

    @Test
    public void with_shouldSetAPrivateIntProperty() throws Exception {
        updatedInstance = objectBuilder.with("id", 32).get();
        assertEquals(32, updatedInstance.getId());
    }
}
