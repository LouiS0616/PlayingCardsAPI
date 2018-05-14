package nsr.loui.playingcards.util;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Utility class to express closed range.
 * Ref. com.google.commons.collect.Range
 */
public final class ClosedIntRange implements Iterable<Integer>, StreamAble<Integer> {
    /**
     * Make range [LOWER..UPPER].
     * @param lower lower limit.
     * @param upper upper limit.
     */
    public ClosedIntRange(int lower, int upper) {
        this.lower_ = lower;
        this.upper_ = upper;
    }

    /**
     * @param value value to inclusion check.
     * @return whether its range contains value or not.
     */
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

    /**
     * @return like this; [6..16].
     */
    @Override
    public String toString() {
        return String.format("[%d..%d]", lower_, upper_);
    }

    private final int lower_, upper_;
}
