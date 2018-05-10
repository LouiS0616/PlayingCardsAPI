package card.imitator.wildcard;

import card.Card;
import card.RankedCard;
import card.imitator.CardImitator;

public class RankImitator extends CardImitator {
    public RankImitator(int rank) {
        this.rank_ = rank;
    }
    @Override
    public boolean isEquivalent(Card card) {
        if(!(card instanceof RankedCard)) {
            return false;
        }

        RankedCard rankedCard = (RankedCard)card;
        return rankedCard.getRank() == this.rank_;
    }

    //
    private final int rank_;
}
