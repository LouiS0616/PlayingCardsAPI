package card.imitator.individual;

import card.Card;
import card.Joker;

public class JokerImitator extends IndividualCardImitator {
    @Override
    public boolean isEquivalent(Card card) {
        return card instanceof Joker;
    }
}
