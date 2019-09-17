package com.challenge.top;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
    List<Integer> top = new ArrayList<>(n);
    input
        .sequential()
        .filter(Objects::nonNull)
        .forEach(i -> {
          if (top.size() < n) {
            addAndSort(top, i);
          } else {
            int last = top.get(n - 1);
            if (i > last) {
              top.remove(n - 1);
              addAndSort(top, i);
            }
          }
        });
    return top;
  }

  private void addAndSort(List<Integer> top, int i) {
    top.add(i);
    top.sort(Comparator.reverseOrder());
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
