package card.imitator.wild;

import card.Card;
import card.RankedCard;
import card.Suit;

public class SuitImitator implements WildCardImitator {
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
