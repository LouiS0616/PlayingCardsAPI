package card.comparator;

import card.Card;
import card.Joker;

import java.util.Comparator;

public abstract class CardComparator implements Comparator<Card> {
    /**
     * This method MUST be called when comparison between jokers.
     */
    protected final int compare(Joker joker1, Joker joker2) {
        return Joker.compareId$for_comparator(joker1, joker2);
    }
}
