package com.challenge.top;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

public class TopFinder {

  /**
   * Returns the top {@literal N} (i.e. largest {@literal N}) integer values in descending order
   * from an input source.
   *
   * @param input the input source of integer values.
   * @param n the amount of the top (i.e. value of {@literal N}).
   * @return the top {@literal N} integer values in descending order.
   * @throws IllegalArgumentException if the input source is null or the amount of the top is less
   * than zero.
   */
  public List<Integer> topN(Stream<Integer> input, int n) {
    checkArguments(input, n);
    if (n == 0) {
      return Collections.emptyList();
    }
    SortedSet<Integer> top = new TreeSet<>(Comparator.reverseOrder());
    input
        .sequential()
        .filter(Objects::nonNull)
        .forEach(i -> {
          if (top.size() < n) {
            top.add(i);
          } else if (i > top.last()) {
            top.remove(top.last());
            top.add(i);
          }
        });
    return new ArrayList<>(top);
  }

  private void checkArguments(Stream<Integer> input, int n) {
    String msg = null;
    if (Objects.isNull(input)) {
      msg = "Argument 'input' in TopFinder.topN() must not be null";
    }
    if (n < 0) {
      msg = "Argument 'n' in TopFinder.topN() must not be less than zero";
    }
    if (Objects.nonNull(msg)) {
      throw new IllegalArgumentException(msg);
    }
  }

}
