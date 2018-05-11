package card.imitator.individual;

import card.Card;
import card.Joker;
import card.imitator.wild.WildCardImitator;

public class JokerImitator implements IndividualCardImitator, WildCardImitator {
    @Override
    public boolean isEquivalent(Card card) {
        return card instanceof Joker;
    }
}
