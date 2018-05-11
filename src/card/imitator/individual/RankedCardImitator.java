package card.imitator.individual;

import card.Card;
import card.RankedCard;
import card.Suit;

public class RankedCardImitator implements IndividualCardImitator {
    public RankedCardImitator(RankedCard model) {
        this(model.getSuit(), model.getRank());
    }
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
