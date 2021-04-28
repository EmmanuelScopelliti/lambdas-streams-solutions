package ex4.model;

public abstract class PointOfInterest {
  String name;
  int length;

  public PointOfInterest(String name, int length) {
    this.name = name;
    this.length = length;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }
}
