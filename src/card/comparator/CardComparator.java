package card.comparator;

import card.Card;
import card.Joker;

import java.util.Comparator;

/**
 * This class handles comparison between jokers, what user should not pay attention.
 * Every card comparator class has to extend this.
 */
public abstract class CardComparator implements Comparator<Card> {
    /**
     * This method MUST be called when comparison between jokers.
     * @param joker1 A joker.
     * @param joker2 A joker.
     * @return Joker.compareId$for_comparator(joker1, joker2).
     */
    protected final int compare(Joker joker1, Joker joker2) {
        return Joker.compareId$for_comparator(joker1, joker2);
    }
}
