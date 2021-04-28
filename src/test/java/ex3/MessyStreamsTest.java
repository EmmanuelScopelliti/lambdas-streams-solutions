package ex3;

import ex3.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static ex3.MessyStreams.*;
import static ex3.model.LiftType.*;
import static ex3.model.SnowType.*;

class MessyStreamsTest {
  static Company company;
  static MountainArea area1, area2, area3;
  static SkiSlope slope1, slope2, slope3, slope4, slope5, slope6, slope7, slope8;
  static SnowCondition snow1, snow2, snow3, snow4, snow5, snow6, snow7, snow8;
  static Lift lift1, lift2, lift3, lift4, lift5;

  @BeforeAll
  static void setUp() {
    snow1 = new SnowCondition(45, true, PACKED_POWDER);
    slope1 = new SkiSlope("Slope 1", 1001, 5, snow1);
    snow2 = new SnowCondition(70, true, PACKED_POWDER);
    slope2 = new SkiSlope("Slope 2", 3000, 1, snow2);
    snow3 = new SnowCondition(50, false, FROZEN_GRANULAR);
    slope3 = new SkiSlope("Slope 3", 4000, 2, snow3);
    lift1 = new Lift("Lift 1", 8000, GONDOLA);
    lift2 = new Lift("Lift 2", 2000, FUNICULAR);
    area1 = new MountainArea("Area 1", List.of(lift1, lift2), List.of(slope1, slope2, slope3));

    snow4 = new SnowCondition(60, true, HARD_PACK);
    slope4 = new SkiSlope("Slope 4", 9000, 3, snow4);
    snow5 = new SnowCondition(90, false, PACKED_POWDER);
    slope5 = new SkiSlope("Slope 5", 30000, 2, snow5);
    lift3 = new Lift("Lift 3", 1000, CHAIRLIFT);
    area2 = new MountainArea("Area 2", List.of(lift3), List.of(slope4, slope5));

    snow6 = new SnowCondition(20, true, HARD_PACK);
    slope6 = new SkiSlope("Slope 6", 16000, 5, snow6);
    snow7 = new SnowCondition(50, true, POWDER);
    slope7 = new SkiSlope("Slope 7", 20000, 1, snow7);
    snow8 = new SnowCondition(15, false, POWDER);
    slope8 = new SkiSlope("Slope 8", 30000, 5, snow8);
    lift4 = new Lift("Lift 4", 3000, GONDOLA);
    lift5 = new Lift("Lift 5", 5000, CABLECAR);
    area3 = new MountainArea("Area 3", List.of(lift4, lift5), List.of(slope6, slope7, slope8));

    company = new Company("Best Slopes srl", List.of(area1, area2, area3));
  }

  @Test
  void averageSlopeLengthOfMountainArea1Is2667() {
    assertThat(averageSlopeLength(area1)).isEqualTo(2667);
  }

  @Test
  void averageSlopeLengthOfMountainArea2Is19500() {
    assertThat(averageSlopeLength(area2)).isEqualTo(19500);
  }

  @Test
  void averageSlopeLengthOfMountainArea3Is22000() {
    assertThat(averageSlopeLength(area3)).isEqualTo(22000);
  }

  @Test
  void mostDifficultLongSlopesArea1() {
    assertThat(mostDifficultLongSlopes(area1)).isEmpty();
  }

  @Test
  void mostDifficultLongSlopesArea2() {
    assertThat(mostDifficultLongSlopes(area2)).containsExactly(slope5);
  }

  @Test
  void mostDifficultLongSlopesArea3() {
    assertThat(mostDifficultLongSlopes(area3)).containsExactly(slope6, slope8);
  }

  @Test
  void mostCommonSnowTypeIsPackedPowder() {
    assertThat(mostCommonSnowType(company)).isEqualTo(PACKED_POWDER);
  }

  @Test
  void area1HasGondola() {
    assertThat(hasGondola(area1)).isTrue();
  }

  @Test
  void area2HasGondola() {
    assertThat(hasGondola(area2)).isFalse();
  }

  @Test
  void area3HasGondola() {
    assertThat(hasGondola(area3)).isTrue();
  }

  @Test
  void shouldGetAllPois() {
    Object[] pois = {lift1, lift2, lift3, lift4, lift5,
            slope1, slope2, slope3, slope4, slope5, slope6, slope7, slope8};

    assertThat(getAllPois(company)).containsExactly(pois);
  }
}