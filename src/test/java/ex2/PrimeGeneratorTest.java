package ex2;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static ex2.Generator.generatePrimesBetween;

public class PrimeGeneratorTest {
  @Test
  void shouldGenerateNoPrimeNumber() {
    int[] primes = generatePrimesBetween(1, 1);
    assertThat(primes).isEmpty();
  }

  @Test
  void shouldGenerateTwoThreeFive() {
    int[] primes = generatePrimesBetween(1, 5);
    assertThat(primes).isEqualTo(new int[]{2, 3, 5});
  }

  @Test
  void shouldGenerateSevenElevenThirteen() {
    int[] primes = generatePrimesBetween(6, 16);
    assertThat(primes).isEqualTo(new int[]{7, 11, 13});
  }

  @Test
  void shouldGeneratePrimesBetween25And99() {
    int[] primes = generatePrimesBetween(25, 99);
    assertThat(primes).isEqualTo(new int[]{29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97});
  }
}
