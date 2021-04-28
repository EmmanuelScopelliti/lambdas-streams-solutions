package ex3.model;

public class SkiSlope extends PointOfInterest{
  int difficulty;
  SnowCondition snowCondition;

  public SkiSlope(String name, int length, int difficulty, SnowCondition snowCondition) {
    super(name, length);
    this.difficulty = difficulty;
    this.snowCondition = snowCondition;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public SnowCondition getSnowCondition() {
    return snowCondition;
  }

  public void setSnowCondition(SnowCondition snowCondition) {
    this.snowCondition = snowCondition;
  }

  /**
   * Added convenience method
   * */
  public boolean isLong(){
    return getLength() > 15000;
  }
}
