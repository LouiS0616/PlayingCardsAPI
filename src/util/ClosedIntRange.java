package util;

import java.util.Iterator;
import java.util.stream.IntStream;

// Ref. com.google.commons.collect.Range
public final class ClosedIntRange implements Iterable<Integer> {
    public ClosedIntRange(int lower, int upper) {
        this.lower_ = lower;
        this.upper_ = upper;
    }
    public boolean contains(int value) {
        return lower_ <= value && value <= upper_;
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream
            .rangeClosed(lower_, upper_)
            .iterator()
        ;
    }

    @Override
    public String toString() {
        return String.format("[%d..%d]", lower_, upper_);
    }

    private final int lower_, upper_;
}
