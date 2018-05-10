package card.imitator.wild;

import card.Card;
import card.Joker;

// If Java allows Diamond Inheritance, this class is no longer necessary.
public class WildJokerImitator extends WildCardImitator {
    @Override
    public boolean isEquivalent(Card card) {
        return card instanceof Joker;
    }
}
