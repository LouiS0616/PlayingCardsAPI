package card.imitator.individual;

import card.Card;
import card.Joker;
import card.imitator.CardImitator;

public class JokerImitator extends CardImitator {
    @Override
    public boolean isEquivalent(Card card) {
        return card instanceof Joker;
    }
}
