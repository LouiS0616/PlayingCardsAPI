package cards.unordered;

import cards.Cards;

public class Hand extends UnorderedCards {
    public Hand(Cards from, int initialNumOfHand) {
        pickFrom(from, initialNumOfHand);
    }
}
