package loui.playingcards.card.imitator;

import loui.playingcards.card.Card;
import loui.playingcards.card.Joker;

/**
 * This class imitate a joker or jokers.
 * Because Joker can be regarded as a loui.playingcards.card and loui.playingcards.card type.
 */
public class JokerImitator implements IndividualCardImitator, WildCardImitator {
    @Override
    public boolean isEquivalent(Card card) {
        return card instanceof Joker;
    }
}
