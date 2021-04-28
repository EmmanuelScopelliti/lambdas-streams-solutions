package ex1;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SimplePipelines {

  /**
   * Returns the total number of letters of names that have five or more letters.
   */
  public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
    return Arrays.stream(names)
            .filter(name -> name.length() > 5)
            .mapToInt(String::length)
            .sum();
  }

  /**
   * Returns a sorted copy of the array.
   */
  public static String[] getSortedCopy(String... names) {
    return Arrays.stream(names)
            .sorted()
            .toArray(String[]::new);
  }

  /**
   * Returns a list of strings created as a result of flattening the input lists of lists.
   */
  public static List<String> transform(List<List<String>> listOfLists) {
    return listOfLists
            .stream()
            .flatMap(List::stream)
            .collect(toList());
  }

  /**
   * Returns an optional containing the oldest person in the input array (if any)
   */
  public static Optional<Person> getOldest(Person... people) {
    return Arrays.stream(people)
            .min(comparing(p -> p.dateOfBirth));
  }

  /**
   * Returns a list containing only adults (age >= 18)
   */
  public static List<Person> getAdults(Person... people) {
    return Arrays.stream(people)
            .filter(person -> person.getAge() >= 18)
            .collect(toList());
  }

  /**
   * Returns a string containing the values in the input array separated by semicolons and between double quotes
   */
  public static String getSemicolonSeparatedQuotedValues(Object... values) {
    return Arrays.stream(values)
            .map(value -> "\"" + value + "\"")
            .collect(joining(";"));
  }

}
