package ex2;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static ex2.Generator.*;

class FibonacciGeneratorTest {

  @Test
  void shouldGenerateEmptyFibonacciSequence() {
    long[] fibonacci = generateFibonacciSequence(0);
    assertThat(fibonacci).isEqualTo(new long[]{});
  }

  @Test
  void shouldGenerateFibonacciSequenceSizeOne() {
    long[] fibonacci = generateFibonacciSequence(1);
    assertThat(fibonacci).isEqualTo(new long[]{1});
  }

  @Test
  void shouldGenerateFibonacciSequenceSizeTwo() {
    long[] fibonacci = generateFibonacciSequence(2);
    assertThat(fibonacci).isEqualTo(new long[]{1, 1});
  }

  @Test
  void shouldGenerateFibonacciSequenceSizeThree() {
    long[] fibonacci = generateFibonacciSequence(3);
    assertThat(fibonacci).isEqualTo(new long[]{1, 1, 2});
  }

  @Test
  void shouldGenerateFibonacciSequenceSizeFour() {
    long[] fibonacci = generateFibonacciSequence(4);
    assertThat(fibonacci).isEqualTo(new long[]{1, 1, 2, 3});
  }

  @Test
  void shouldGenerateFibonacciSequenceSizeTen() {
    long[] fibonacci = generateFibonacciSequence(10);
    assertThat(fibonacci).isEqualTo(new long[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55});
  }

}