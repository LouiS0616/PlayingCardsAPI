package loui.playingcards.card.comparator;

import loui.playingcards.card.Card;
import loui.playingcards.card.Joker;

import java.util.Comparator;

/**
 * This class handles comparison between jokers, what user does not have to pay attention.
 * Every card comparator class MUST extend this.
 */
public abstract class CardComparator implements Comparator<Card> {
    /**
     * This method MUST be called when comparison needed between jokers.
     * @param joker1 A joker.
     * @param joker2 A joker.
     * @return result of Joker.compareId$for_comparator(joker1, joker2).
     */
    protected final int compare(Joker joker1, Joker joker2) {
        return Joker.compareId$for_comparator(joker1, joker2);
    }
}
