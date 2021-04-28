package ex3.model;

public class SnowCondition {
  int baseSnow;
  boolean isGroomed;
  SnowType snowType;

  public SnowCondition(int baseSnow, boolean isGroomed, SnowType snowType) {
    this.baseSnow = baseSnow;
    this.isGroomed = isGroomed;
    this.snowType = snowType;
  }

  public int getBaseSnow() {
    return baseSnow;
  }

  public void setBaseSnow(int baseSnow) {
    this.baseSnow = baseSnow;
  }

  public boolean isGroomed() {
    return isGroomed;
  }

  public void setGroomed(boolean groomed) {
    isGroomed = groomed;
  }

  public SnowType getSnowType() {
    return snowType;
  }

  public void setSnowType(SnowType snowType) {
    this.snowType = snowType;
  }
}
