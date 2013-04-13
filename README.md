# Object Builder

A simple object builder for Java application testing

## Example

With the following class
```
private class Pet {
  private int legCount;
  private String name;

  public int getLegCount() {
    return legCount;
  }

  public String getName() {
    return name;
  }
}
```

You can instantiate it using the following:
```
Pet myPet =
  new ObjectBuilder<Pet>(new Pet())
    .with("legCount", 8)
    .with("name", "Spidey")
    .build();
```

You can also subclass the builder for cleaner usage, and setting defaults:
```
class PetBuilder extends ObjectBuilder<Pet>{
    public PetBuilder(Pet instance) {
      super(instance);
      setDefault("legCount", 4);
    }
}

Pet myOtherPet =
  new PetBuilder(new Pet())
    .with("name", "Rufus")
    .build();
```

In this case legCount will be set to 4 by default
