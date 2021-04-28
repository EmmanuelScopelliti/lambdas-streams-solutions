package ex2;

import java.util.function.LongSupplier;

public class FibonnaciSupplier implements LongSupplier {
  int previous = 0;
  int next = 1;

  @Override
  public long getAsLong() {
    int value = next;
    next = next + previous;
    previous = value;
    return value;
  }
}
