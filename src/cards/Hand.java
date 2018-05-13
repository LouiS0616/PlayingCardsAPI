package cards;

import card.comparator.CardComparator;
import card.comparator.DefaultCardComparator;
import observer.Observer;

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
