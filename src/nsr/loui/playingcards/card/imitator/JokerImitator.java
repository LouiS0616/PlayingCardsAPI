package nsr.loui.playingcards.card.imitator;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.Joker;

/**
 * This class imitate a joker or jokers.
 * Because Joker can be regarded as a card and card type.
 */
public class JokerImitator implements IndividualCardImitator, WildCardImitator {
    @Override
    public boolean isEquivalent(Card card) {
        return card instanceof Joker;
    }
}
