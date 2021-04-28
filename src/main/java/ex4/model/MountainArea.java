package ex4.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class MountainArea {
  String name;
  Set<Lift> lifts;
  Set<SkiSlope> slopes;

  public MountainArea(String name, Collection<Lift> lifts, Collection<SkiSlope> slopes) {
    this.name = name;
    this.lifts = new HashSet<>(lifts);
    this.slopes = new HashSet<>(slopes);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Lift> getLifts() {
    return lifts;
  }

  public void setLifts(Set<Lift> lifts) {
    this.lifts = lifts;
  }

  public Set<SkiSlope> getSlopes() {
    return slopes;
  }

  public void setSlopes(Set<SkiSlope> slopes) {
    this.slopes = slopes;
  }

  /**
   * Added method
   */
  public Set<PointOfInterest> getPointOfInterests() {
    Set<PointOfInterest> pois = new HashSet<>(getLifts());
    pois.addAll(getSlopes());
    return pois;
  }

  /**
   * Added method
   */
  public List<SkiSlope> getLongSlopes() {
    return getSlopes()
            .stream()
            .filter(SkiSlope::isLong)
            .collect(toList());
  }
}
