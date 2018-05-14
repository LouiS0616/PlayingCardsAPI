package nsr.loui.playingcards.util;

import java.util.Collection;
import java.util.List;

public class CollectionUtil {
    public static <T> T popElem(Collection<T> from, T target) {
        from.remove(target);
        return target;
    }
    public static <T> T popElem(List<T> from, T target) {
        return from.remove(from.indexOf(target));
    }
}
