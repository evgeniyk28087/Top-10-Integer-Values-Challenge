# Top 10 Integer Values Challenge

# Goal
Goal is to write Java method that finds the top 10 (i.e. largest 10) integer values from an input
source and returns them as a list. The values should be returned in descending order, e.g. if the
input is [1, 3, 9, 4] the method should return [9, 4, 3, 1].
```java
public List<Integer> topN(Stream<Integer> input);
```

# Expectations
* You should submit a Java project with gradle as build tool.
* You can use any library of your choice to make your work easier for you, but we have no
  expectations. The only constraint is that the topN implementation should not be coming
  from a library. In general it should be possible to write without the need of additional
  libraries (except JUnit for unit tests). But this is up to you.

# Exercise
1. Write an implementation for the topN method with the given signature (or a similar
signature, if you prefer a different one that follows the same idea)
2. Write unit tests that verifies the correctness of your implementation.
3. Verify that your implementation works for 100 Million Integer values as input, e.g.
```java
Stream.iterate(0, value -> value + 1).limit(100_000_000);
```

# Additional Question
Could this be distributed and how would this have to be done? This is not part of the
programming challenge.
