package loui.playingcards.cards;

import loui.playingcards.card.comparator.CardComparator;
import loui.playingcards.card.comparator.DefaultCardComparator;
import loui.playingcards.observer.Observer;

/**
 * Auto sorted hand.
 * Most portable class to use.
 */
public final class Hand extends AutoSortedCards {
    public Hand(String name) {
        this(name, Observer.STUB, new DefaultCardComparator());
    }
    public Hand(String name, CardComparator comparator) {
        this(name, Observer.STUB, comparator);
    }
    public Hand(String name, Observer observer) {
        this(name, observer, new DefaultCardComparator());
    }
    public Hand(String name, Observer observer, CardComparator comparator) {
        super(name, observer, comparator);
    }
}
