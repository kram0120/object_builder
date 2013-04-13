# Object Builder

A simple object builder for Java application testing

## Example

With the following class
```
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
```

You can instantiate it using the following:
```
DomainModel testModel =
  new ObjectBuilder<DomainModel>(new DomainModel())
    .with("id", 33)
    .with("object", "Hello World!")
    .get();
```

