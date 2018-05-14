package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.card.comparator.DefaultCardComparator;
import nsr.loui.playingcards.observer.Observer;

/**
 * Auto sorted hand.
 * Most portable class to use.
 */
public final class Hand extends AutoSortedCards {
    /**
     * The default values are as below:
     * comparator - instance of DefaultCardComparator.
     * observer - Observer.STUB.
     * @param name name of cards used for print info.
     */
    public Hand(String name) {
        this(name, Observer.STUB, new DefaultCardComparator());
    }
    public Hand(String name, CardComparator comparator) {
        this(name, Observer.STUB, comparator);
    }
    public Hand(String name, Observer observer) {
        this(name, observer, new DefaultCardComparator());
    }

    /**
     * @param name name of cards used for print info.
     * @param observer observer. DON'T pass null, DO use stub instead.
     * @param comparator comparator used for auto-sort.
     */
    public Hand(String name, Observer observer, CardComparator comparator) {
        super(name, observer, comparator);
    }
}
