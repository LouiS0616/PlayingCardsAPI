package loui.playingcards.util;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Ref. com.google.commons.collect.Range
 */
public final class ClosedIntRange implements Iterable<Integer>, StreamAble<Integer> {
    public ClosedIntRange(int lower, int upper) {
        this.lower_ = lower;
        this.upper_ = upper;
    }
    public boolean contains(int value) {
        return lower_ <= value && value <= upper_;
    }

    @Override
    public Iterator<Integer> iterator() {
        return intStream().iterator();
    }

    @Override
    public Stream<Integer> stream() {
        return intStream().boxed();
    }
    public IntStream intStream() {
        return IntStream.rangeClosed(lower_, upper_);
    }

    @Override
    public String toString() {
        return String.format("[%d..%d]", lower_, upper_);
    }

    private final int lower_, upper_;
}
