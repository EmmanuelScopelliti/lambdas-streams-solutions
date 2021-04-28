package ex3;

import ex3.model.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

@SuppressWarnings("ALL")
public class MessyStreams {

  /**
   * - Multiline lambda
   * - flatMap just to invoke stream()
   */
  public static List<PointOfInterest> getAllPois(Company company) {

//    return company.getManagedAreas()
//            .stream()
//            .map(mountainArea -> {
//              Set<Lift> lifts = mountainArea.getLifts();
//              Set<SkiSlope> slopes = mountainArea.getSlopes();
//              Set<PointOfInterest> pois = new HashSet<>(lifts);
//              pois.addAll(slopes);
//              return pois;
//            })
//            .flatMap(set -> set.stream())
//            .collect(Collectors.toList());

    return company.getManagedAreas()
            .stream()
            .flatMap(area -> area.getPointOfInterests().stream())
            .collect(toList());
  }

  /**
   * - Filter, collect, and size instead of anyMatch
   */
  public static boolean hasGondola(MountainArea area) {
//    return area.getLifts()
//            .stream()
//            .filter(lift -> lift.getType() == LiftType.GONDOLA)
//            .collect(Collectors.toList())
//            .size() > 0;

    return area.getLifts()
            .stream()
            .anyMatch(Lift::isGondola);

  }

  /**
   * - Stream within a stream
   * - Reusable set (long slopes)
   */
  public static List<SkiSlope> mostDifficultLongSlopes(MountainArea area) {

//    return area.getSlopes()
//            .stream()
//            .filter(slope -> slope.getLength() > 15000)
//            .filter(slope ->
//                    slope.getDifficulty() == area.getSlopes()
//                            .stream()
//                            .filter(slope2 -> slope2.getLength() > 15000)
//                            .mapToInt(slope2 -> slope2.getDifficulty())
//                            .max()
//                            .orElse(0))
//            .collect(Collectors.toList());

    List<SkiSlope> longSlopes = area.getLongSlopes();

    int highestDifficulty = longSlopes.stream()
            .mapToInt(SkiSlope::getDifficulty)
            .max()
            .orElse(-1);

    if (highestDifficulty == -1)
      return Collections.emptyList();

    return longSlopes
            .stream()
            .filter(slope -> slope.getDifficulty() == highestDifficulty)
            .collect(toList());
  }

  /**
   * - Excessively long stream
   * - flatMap just to invoke stream()
   * - Sorted, reverse, instead of invoking max()
   */
  public static SnowType mostCommonSnowType(Company company) {
//    return company.getManagedAreas()
//            .stream()
//            .map(mountainArea -> mountainArea.getSlopes())
//            .flatMap(list -> list.stream())
//            .map(skiSlope -> skiSlope.getSnowCondition())
//            .map(snowCondition -> snowCondition.getSnowType())
//            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//            .entrySet()
//            .stream()
//            .sorted((entry1, entry2) -> (int) (entry1.getValue() - entry2.getValue()))
//            .collect(Collectors.toCollection(LinkedList::new))
//            .descendingIterator()
//            .next()
//            .getKey();

    Map<SnowType, Long> snowTypeFrequencyMap = company.getManagedAreas()
            .stream()
            .flatMap(area -> area.getSlopes().stream())
            .map(SkiSlope::getSnowCondition)
            .map(SnowCondition::getSnowType)
            .collect(groupingBy(identity(), counting()));

    return snowTypeFrequencyMap
            .entrySet()
            .stream()
            .max(comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
  }

  /**
   * - Generic stream using reduce instead of IntStream and average
   */
  public static double averageSlopeLength(MountainArea area) {
//    return (double) area.getSlopes()
//            .stream()
//            .map(skiSlope -> skiSlope.getLength())
//            .reduce(0, (x, y) -> x + y) / area.getSlopes().size();

    return area.getSlopes()
            .stream()
            .mapToInt(SkiSlope::getLength)
            .average()
            .orElse(0);
  }

}
