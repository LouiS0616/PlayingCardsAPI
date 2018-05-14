package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.card.comparator.DefaultCardComparator;
import nsr.loui.playingcards.observer.Observer;

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
