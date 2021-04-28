package ex2;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static ex2.Generator.generatePerfectSquares;

public class PerfectSquareGeneratorTest {
  @Test
  void shouldGenerateNoPerfectSquareGivenNegativeInput() {
    int[] squares = generatePerfectSquares(-1);
    assertThat(squares).hasLength(0);
  }

  @Test
  void shouldGenerateNoPerfectSquareGivenZero() {
    int[] squares = generatePerfectSquares(0);
    assertThat(squares).hasLength(0);
  }

  @Test
  void shouldGenerateOnePerfectSquare() {
    int[] squares = generatePerfectSquares(1);
    assertThat(squares).isEqualTo(new int[]{1});
  }

  @Test
  void shouldGenerateTwoPerfectSquares() {
    int[] squares = generatePerfectSquares(2);
    assertThat(squares).isEqualTo(new int[]{1, 4});
  }

  @Test
  void shouldGenerateFivePerfectSquares() {
    int[] squares = generatePerfectSquares(5);
    assertThat(squares).isEqualTo(new int[]{1, 4, 9, 16, 25});
  }

  @Test
  void shouldGenerateTenPerfectSquares() {
    int[] squares = generatePerfectSquares(10);
    assertThat(squares).isEqualTo(new int[]{1, 4, 9, 16, 25, 36, 49, 64, 81, 100});
  }
}
