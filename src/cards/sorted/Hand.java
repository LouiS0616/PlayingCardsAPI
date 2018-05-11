package cards.sorted;

import card.comparator.CardComparator;
import card.comparator.DefaultCardComparator;
import cards.Cards;
import cards.Observer;

public class Hand extends AutoSortedCards {
    public Hand(String name, Cards from, int initialNumOfHand) {
        this(name, Observer.STUB, new DefaultCardComparator(), from, initialNumOfHand);
    }
    public Hand(String name, CardComparator comparator, Cards from, int initialNumOfHand) {
        this(name, Observer.STUB, comparator, from, initialNumOfHand);
    }
    public Hand(String name, Observer observer, Cards from, int initialNumOfHand) {
        this(name, observer, new DefaultCardComparator(), from, initialNumOfHand);
    }
    public Hand(String name, Observer observer, CardComparator comparator, Cards from, int initialNumOfHand) {
        super(name, observer, comparator);
        pickFrom(from, initialNumOfHand);
    }
}
