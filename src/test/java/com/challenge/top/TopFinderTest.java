package com.challenge.top;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link TopFinder}.
 */
@RunWith(JUnit4.class)
public class TopFinderTest {

  private TopFinder topFinder = new TopFinder();

  /**
   * Verifies {@link TopFinder#topN(Stream, int)} with N = 10 and the amount of input source equals
   * 100_000_000.
   */
  @Test
  public void testTop10From100Million() {
    int n = 10;
    int limit = 100_000_000;
    List<Integer> expectedTop = Stream.iterate(limit - 1, value -> value - 1)
        .limit(n)
        .collect(Collectors.toList());

    Stream<Integer> input = Stream.iterate(0, value -> value + 1).limit(limit);
    List<Integer> top = topFinder.topN(input, n);

    assertsTop(n, expectedTop, top);
  }

  /**
   * Verifies {@link TopFinder#topN(Stream, int)} with N = 10 and the amount of input source equals
   * 20.
   */
  @Test
  public void testTop10From20() {
    int n = 10;
    List<Integer> expectedTop = new ArrayList<>();
    List<Integer> input = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      input.add(i);

      int intInTop = i + 100;
      input.add(intInTop);
      expectedTop.add(intInTop);
    }
    Collections.reverse(expectedTop);

    List<Integer> top = topFinder.topN(input.stream(), n);

    assertsTop(n, expectedTop, top);
  }

  /**
   * Verifies {@link TopFinder#topN(Stream, int)} with N = 1 and the amount of input source equals
   * 10.
   */
  @Test
  public void testTop1From10() {
    int n = 1;
    int max = 100;
    List<Integer> expectedTop = Collections.singletonList(max);

    Stream<Integer> input = Stream.of(1, 2, 3, max, 4, 5, 6, 7, 8, 9);
    List<Integer> top = topFinder.topN(input, n);

    assertsTop(n, expectedTop, top);
  }

  /**
   * Verifies {@link TopFinder#topN(Stream, int)} with N = 0.
   */
  @Test
  public void testTop0() {
    int n = 0;
    Stream<Integer> input = Stream.of(1, 2, 3);
    List<Integer> top = topFinder.topN(input, n);

    assertsTop(n, Collections.emptyList(), top);
  }

  /**
   * Verifies {@link TopFinder#topN(Stream, int)} with the input source is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTopWithInputIsNull() {
    topFinder.topN(null, 10);
  }

  /**
   * Verifies {@link TopFinder#topN(Stream, int)} with N = -1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTopWithNIsLessThanZero() {
    topFinder.topN(Stream.of(1, 2, 3), -1);
  }

  private void assertsTop(int n, List<Integer> expectedTop, List<Integer> top) {
    Assert.assertNotNull(top);
    Assert.assertEquals(n, top.size());
    Assert.assertEquals(expectedTop, top);
  }

}
