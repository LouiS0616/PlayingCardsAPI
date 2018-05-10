package card.imitator.wildcard;

import card.Card;
import card.RankedCard;
import card.Suit;
import card.imitator.CardImitator;

public class SuitImitator extends CardImitator {
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
