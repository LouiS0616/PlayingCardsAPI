package nsr.loui.playingcards.util;

import java.util.Collection;
import java.util.List;

public class CollectionUtil {
    /**
     * Pop TARGET elem and remove it from FROM.
     * @param from where TARGET will be popped and removed from.
     * @param target target element.
     * @param <T> collection elem type.
     * @return popped TARGET.
     */
    public static <T> T popElem(Collection<T> from, T target) {
        from.remove(target);
        return target;
    }

    /**
     * Pop TARGET elem and remove it from FROM.
     * @param from where TARGET will be popped and removed from.
     * @param target target element.
     * @param <T> list elem type.
     * @return popped TARGET.
     */
    public static <T> T popElem(List<T> from, T target) {
        return from.remove(from.indexOf(target));
    }
}
