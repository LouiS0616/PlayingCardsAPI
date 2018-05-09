package card.imitator;

import card.Card;
import card.Joker;

public class JokerImitator extends CardImitator {
    @Override
    public boolean isEquivalent(Card card) {
        return card instanceof Joker;
    }
}
