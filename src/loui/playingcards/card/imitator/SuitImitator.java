package loui.playingcards.card.imitator;

import loui.playingcards.card.Card;
import loui.playingcards.card.RankedCard;
import loui.playingcards.card.Suit;

/**
 * This class imitate suit type of card.
 * For example, SuitImitator(Suit.SPADE) can imitate "Spade 3", "Spade king" and more.
 */
public class SuitImitator implements WildCardImitator {
    /**
     * Make instance what can imitate model.
     * @param model A Ranked Card behaves suit model.
     */
    public SuitImitator(RankedCard model) {
        this(model.getSuit());
    }

    /**
     * @param suit Suit to imitate.
     */
    public SuitImitator(Suit suit) {
        this.suit_ = suit;
    }

    @Override
    public boolean isEquivalent(Card card) {
        if(!(card instanceof RankedCard)) {
            return false;
        }

        RankedCard rankedCard = (RankedCard)card;
        return rankedCard.getSuit() == this.suit_;
    }

    //
    private final Suit suit_;
}
