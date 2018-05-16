package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.observer.Observer;

import java.util.TreeSet;

/**
 * You can draw a card randomly, but you cannot draw top card of cards.
 * Auto-sorting concept is close to unordered rather than ordered that, so this extends that.
 *
 * This class has cards as SortedSet.
 */
public abstract class AutoSortedCards extends UnorderedCards {
    //
    // Generate methods

    /**
     * @param name name of cards used for print info.
     * @param observer observer. DON'T pass null, DO use stub instead.
     * @param comparator comparator used for auto-sorting.
     */
    protected AutoSortedCards(String name, Observer observer, CardComparator comparator) {
        super(name, observer, new TreeSet<>(comparator));
    }
}
