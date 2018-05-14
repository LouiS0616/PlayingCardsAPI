package nsr.loui.playingcards.card.imitator;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.RankedCard;
import nsr.loui.playingcards.card.Suit;

/**
 * This class imitate cards what have same suit.
 * For example, SuitImitator(Suit.SPADE) can imitate "Spade 3", "Spade king" and more.
 */
public class SuitImitator implements WildCardImitator {
    /**
     * Make instance what can imitate model.
     * @param model a Ranked Card behaves as suit imitator model.
     */
    public SuitImitator(RankedCard model) {
        this(model.getSuit());
    }

    /**
     * @param suit suit to imitate.
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
