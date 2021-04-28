package ex4.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Company {
  String name;
  Set<MountainArea> managedAreas;

  public Company(String name, Collection<MountainArea> areas) {
    this.name = name;
    this.managedAreas = new HashSet<>(areas);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<MountainArea> getManagedAreas() {
    return managedAreas;
  }

  public void setManagedAreas(Set<MountainArea> managedAreas) {
    this.managedAreas = managedAreas;
  }
}
