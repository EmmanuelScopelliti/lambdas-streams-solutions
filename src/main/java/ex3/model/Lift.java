package ex3.model;

public class Lift extends PointOfInterest {
  LiftType type;

  public Lift(String name, int length, LiftType type) {
    super(name, length);
    this.type = type;
  }

  public LiftType getType() {
    return type;
  }

  public void setType(LiftType type) {
    this.type = type;
  }

  /**
   * Added convenience method
   * */
  public boolean isGondola(){
    return getType() == LiftType.GONDOLA;
  }
}
