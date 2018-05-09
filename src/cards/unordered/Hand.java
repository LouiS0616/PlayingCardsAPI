package cards.unordered;

import cards.Cards;
import cards.Observer;

public class Hand extends AutoSortedCards {
    public Hand(String name, Cards from, int initialNumOfHand) {
        this(name, Observer.STUB, from, initialNumOfHand);
    }
    public Hand(String name, Observer observer, Cards from, int initialNumOfHand) {
        super(name, observer);
        pickFrom(from, initialNumOfHand);
    }
}
