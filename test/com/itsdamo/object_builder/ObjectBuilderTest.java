package com.itsdamo.object_builder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: dam5s
 * Date: 8/04/13
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class ObjectBuilderTest {

    private class Animal {
        private int legCount;
        private String name;

        public int getLegCount() {
            return legCount;
        }

        public String getName() {
            return name;
        }
    }

    private class Dog extends Animal {
        private boolean likesBarking;

        public boolean getLikesBarking() {
            return likesBarking;
        }
    }

    private class AnimalBuilder extends ObjectBuilder<Animal> {
        public AnimalBuilder(Animal instance) {
            super(instance);
        }
    }

    private class DogBuilder extends ObjectBuilder<Dog> {
        public DogBuilder(Dog instance) {
            super(instance);
            setDefault("likesBarking", true);
        }
    }

    private AnimalBuilder animalBuilder;

    @Test
    public void build_shouldReturnTheInstance() {
        Animal animal = new Animal();
        animalBuilder = new AnimalBuilder(animal);

        assertEquals(animal, animalBuilder.build());
    }

    @Test
    public void with_shouldSetAPrivateObjectProperty() throws Exception {
        animalBuilder = new AnimalBuilder(new Animal());

        Animal builtAnimal = animalBuilder.with("name", "Rodolf").build();
        assertEquals("Rodolf", builtAnimal.getName());
    }

    @Test
    public void with_shouldSetAPrivateIntProperty() throws Exception {
        animalBuilder = new AnimalBuilder(new Animal());

        Animal builtAnimal = animalBuilder.with("legCount", 8).build();
        assertEquals(8, builtAnimal.getLegCount());
    }

    @Test
    public void with_shouldSetAnInheritedProperty() throws Exception {
        animalBuilder = new AnimalBuilder(new Dog());

        Dog builtDog = (Dog) animalBuilder.with("legCount", 4).build();
        assertEquals(4, builtDog.getLegCount());
    }

    @Test
    public void build_shouldApplyDefaults() throws Exception {
        Dog dog = new DogBuilder(new Dog()).build();
        assertEquals(true, dog.getLikesBarking());
    }

    @Test
    public void build_shouldAllowOverridingDefaults() throws Exception {
        Dog dog = new DogBuilder(new Dog())
                .with("likesBarking", false)
                .build();

        assertEquals(false, dog.getLikesBarking());
    }
}
