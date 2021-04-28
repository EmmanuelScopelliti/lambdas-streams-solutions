package ex1;

import java.time.LocalDate;
import java.time.Period;

public class Person {
  LocalDate dateOfBirth;

  public Person(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public int getAge() {
    return Period.between(dateOfBirth, LocalDate.now()).getYears();
  }

  @Override
  public String toString() {
    return dateOfBirth.toString();
  }
}
