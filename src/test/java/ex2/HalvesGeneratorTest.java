package ex2;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static ex2.Generator.generateHalves;

public class HalvesGeneratorTest {
  @Test
  void shouldGenerateNoHalves() {
    int[] halves = generateHalves(0);
    assertThat(halves).isEqualTo(new int[]{});
  }

  @Test
  void shouldGenerateHalvesStartingFromOne() {
    int[] halves = generateHalves(1);
    assertThat(halves).isEqualTo(new int[]{1});
  }

  @Test
  void shouldGenerateHalvesStartingFromTwo() {
    int[] halves = generateHalves(2);
    assertThat(halves).isEqualTo(new int[]{2, 1});
  }

  @Test
  void shouldGenerateHalvesSizeStartingFromThree() {
    int[] halves = generateHalves(3);
    assertThat(halves).isEqualTo(new int[]{3, 1});
  }

  @Test
  void shouldGenerateHalvesStartingFromFour() {
    int[] halves = generateHalves(4);
    assertThat(halves).isEqualTo(new int[]{4, 2, 1});
  }

  @Test
  void shouldGenerateStartingFromTen() {
    int[] halves = generateHalves(10);
    assertThat(halves).isEqualTo(new int[]{10, 5, 2, 1});
  }

  @Test
  void shouldGenerateStartingFromFourteen() {
    int[] halves = generateHalves(14);
    assertThat(halves).isEqualTo(new int[]{14, 7, 3, 1});
  }
}
