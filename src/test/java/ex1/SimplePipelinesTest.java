package ex1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static ex1.SimplePipelines.*;

class SimplePipelinesTest {

  @Test
  @DisplayName("Testing if [rick, morty, birdperson, gearhead, summer, jerry, beth] returns 24")
  public void testTotalNumberOfLetters1() {
    int total = getTotalNumberOfLettersOfNamesLongerThanFive("rick", "morty", "birdperson", "gearhead", "summer", "jerry", "beth");
    assertThat(total).isEqualTo(24);
  }

  @Test
  @DisplayName("Testing if [rick, morty] returns 0")
  public void testTotalNumberOfLetters2() {
    int total = getTotalNumberOfLettersOfNamesLongerThanFive("rick", "morty");
    assertThat(total).isEqualTo(0);
  }

  @Test
  @DisplayName("Testing if [rick, morty, gearhead, summer, jerry, beth] returns [beth, gearhead, jerry, morty, rick]")
  public void testSortedCopy1() {
    String[] sorted = getSortedCopy("rick", "morty", "gearhead", "jerry", "beth");
    assertThat(sorted).isEqualTo(new String[]{"beth", "gearhead", "jerry", "morty", "rick"});
  }

  @Test
  @DisplayName("Testing if [rick, morty] returns [morty, rick]")
  public void testSortedCopy2() {
    String[] sorted = getSortedCopy("rick", "morty");
    assertThat(sorted).isEqualTo(new String[]{"morty", "rick"});
  }

  @Test
  @DisplayName("Testing if [[rick, morty], [frodo, sam], [neo, trinity]] returns [rick, morty, frodo, sam, neo, trinity]")
  public void testFlatten1() {
    List<String> flat = transform(List.of(List.of("rick", "morty"), List.of("frodo", "sam"), List.of("neo", "trinity")));
    assertThat(flat).containsExactly("rick", "morty", "frodo", "sam", "neo", "trinity");
  }

  @Test
  @DisplayName("Testing if [[rick, morty]] returns [rick, morty]")
  public void testFlatten2() {
    List<String> flat = transform(List.of(List.of("rick", "morty")));
    assertThat(flat).containsExactly("rick", "morty");
  }

  @Test
  @DisplayName("Testing if [1990/04/28, 1992/11/15, 1990/01/21, 1993/10/13] returns 1990/01/21")
  public void testOldest1() {
    Person person1 = new Person(LocalDate.of(1990, Month.APRIL, 28));
    Person person2 = new Person(LocalDate.of(1992, Month.NOVEMBER, 15));
    Person person3 = new Person(LocalDate.of(1990, Month.JANUARY, 21));
    Person person4 = new Person(LocalDate.of(1993, Month.OCTOBER, 13));
    Optional<Person> oldestOpt = getOldest(person1, person2, person3, person4);

    assertThat(oldestOpt).hasValue(person3);
  }

  @Test
  @DisplayName("Testing if [1995/01/01, 1995/01/02] returns 1995/01/01")
  public void testOldest2() {
    Person person1 = new Person(LocalDate.of(1995, Month.JANUARY, 1));
    Person person2 = new Person(LocalDate.of(1995, Month.JANUARY, 2));
    Optional<Person> oldestOpt = getOldest(person1, person2);

    assertThat(oldestOpt).hasValue(person1);
  }

  @Test
  @DisplayName("Testing if [1990/04/28, 2000/11/15, 2005/01/21, 2010/10/13] returns [1990/04/28, 2000/11/15]")
  public void testGetAdults1() {
    Person person1 = new Person(LocalDate.of(1990, Month.APRIL, 28));
    Person person2 = new Person(LocalDate.of(2000, Month.NOVEMBER, 15));
    Person person3 = new Person(LocalDate.of(2005, Month.JANUARY, 21));
    Person person4 = new Person(LocalDate.of(2010, Month.OCTOBER, 13));
    List<Person> adults = getAdults(person1, person2, person3, person4);

    assertThat(adults).containsExactly(person1, person2);
  }

  @Test
  @DisplayName("Testing if [1995/01/01] returns 1995/01/01")
  public void testGetAdults2() {
    Person person1 = new Person(LocalDate.of(1995, Month.JANUARY, 1));
    List<Person> adults = getAdults(person1);

    assertThat(adults).containsExactly(person1);
  }

  @Test
  @DisplayName("Testing if [rick, morty, birdperson] returns \"rick\";\"morty\";\"birdperson\"")
  public void testSeparatedValues1(){
    String output = getSemicolonSeparatedQuotedValues("rick", "morty", "birdperson");
    assertThat(output).isEqualTo("\"rick\";\"morty\";\"birdperson\"");
  }

  @Test
  @DisplayName("Testing if [rick, 100, true] returns \"rick\";\"100\";\"true\"")
  public void testSeparatedValues2(){
    String output = getSemicolonSeparatedQuotedValues("rick", 100, true);
    assertThat(output).isEqualTo("\"rick\";\"100\";\"true\"");
  }

}