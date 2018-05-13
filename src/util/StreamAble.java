package util;

import java.util.stream.Stream;

public interface StreamAble<T> {
    Stream<T> stream();
}
