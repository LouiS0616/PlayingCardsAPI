package cards.sorted;

import card.comparator.CardComparator;
import card.comparator.DefaultCardComparator;
import cards.PlayableCards;
import cards.Observer;

public class Hand extends AutoSortedPlayableCards {
    public Hand(String name, PlayableCards from, int initialNumOfHand) {
        this(name, Observer.STUB, new DefaultCardComparator(), from, initialNumOfHand);
    }
    public Hand(String name, CardComparator comparator, PlayableCards from, int initialNumOfHand) {
        this(name, Observer.STUB, comparator, from, initialNumOfHand);
    }
    public Hand(String name, Observer observer, PlayableCards from, int initialNumOfHand) {
        this(name, observer, new DefaultCardComparator(), from, initialNumOfHand);
    }
    public Hand(String name, Observer observer, CardComparator comparator, PlayableCards from, int initialNumOfHand) {
        super(name, observer, comparator);
        pickFrom(from, initialNumOfHand);
    }
}
