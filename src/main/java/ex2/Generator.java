package ex2;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Generator {

  /**
   * Generates an array containing the n-first perfect squares, starting from 1.
   * If n <= 0, should return an array of size 0.
   */
  public static int[] generatePerfectSquares(int n) {
    return IntStream.range(1, n + 1)
            .map(x -> (int) Math.pow(x, 2))
            .toArray();
  }

  /**
   * Generates an array containing the n-first numbers in the fibonnaci sequence, starting from 1.
   */
  public static long[] generateFibonacciSequence(int n) {
    return LongStream.generate(new FibonnaciSupplier())
            .limit(n)
            .toArray();
  }

  /**
   * Generates an array by recursively dividing the input value by two, until 1.
   * For instance:
   * - generateHalves(5) returns [5, 2, 1]
   * - generateHalves(14) returns [14, 7, 3, 1]
   */
  public static int[] generateHalves(int firstValue) {
    if (firstValue < 0)
      return new int[0];

    return IntStream.iterate(firstValue, x -> x / 2)
            .limit(firstValue)
            .filter(x -> x > 0)
            .toArray();
  }

  /**
   * Generates an array containing all prime numbers within the given range.
   */
  public static int[] generatePrimesBetween(int minInclusive, int maxInclusive) {
    int min = (minInclusive > 0) ? minInclusive : 1;
    int max = (maxInclusive >= min) ? maxInclusive : 1;

    return IntStream.rangeClosed(min, max)
            .filter(Generator::isPrime)
            .toArray();
  }

  /**
   * Method from https://www.baeldung.com/java-prime-numbers
   */
  private static boolean isPrime(int x) {
    return x > 1 &&
            IntStream.rangeClosed(2, (int) Math.sqrt(x))
                    .noneMatch(n -> (x % n == 0));
  }


}
