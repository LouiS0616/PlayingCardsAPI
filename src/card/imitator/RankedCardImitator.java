package card.imitator;

import card.Card;
import card.RankedCard;
import card.Suit;

/**
 * This class imitate RankedCard.
 */
public class RankedCardImitator implements IndividualCardImitator {
    /**
     * Make instance what can imitate model.
     * @param model A Ranked Card behaves model.
     */
    public RankedCardImitator(RankedCard model) {
        this(model.getSuit(), model.getRank());
    }

    /**
     * @param suit Suit to imitate.
     * @param rank Rank to imitate.
     */
    public RankedCardImitator(Suit suit, int rank) {
        this.suit_ = suit;
        this.rank_ = rank;
    }

    @Override
    public boolean isEquivalent(Card card) {
        if(!(card instanceof RankedCard)) {
            return false;
        }

        RankedCard rankedCard = (RankedCard)card;
        return rankedCard.getRank() == this.rank_
            && rankedCard.getSuit() == this.suit_
        ;
    }

    //
    private final Suit suit_;
    private final int rank_;
}
