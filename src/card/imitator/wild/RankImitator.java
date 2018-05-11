package card.imitator.wild;

import card.Card;
import card.RankedCard;

public class RankImitator implements WildCardImitator {
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
