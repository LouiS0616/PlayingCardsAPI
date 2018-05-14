package nsr.loui.playingcards.card.imitator;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.RankedCard;
import nsr.loui.playingcards.card.Suit;

/**
 * This class imitate RankedCard.
 */
public class RankedCardImitator implements IndividualCardImitator {
    /**
     * Make instance what can imitate model.
     * @param model a Ranked Card behaves as imitator model.
     */
    public RankedCardImitator(RankedCard model) {
        this(model.getSuit(), model.getRank());
    }

    /**
     * @param suit suit to imitate.
     * @param rank rank to imitate.
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
