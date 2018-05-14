package nsr.loui.playingcards.card.imitator;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.RankedCard;

/**
 * This class imitate rank type of card.
 * For example, RankImitator(3) can imitate "Spade 3", "Heart 3" and more.
 */
public class RankImitator implements WildCardImitator {
    /**
     * Make instance what can imitate model.
     * @param model A Ranked Card behaves rank model.
     */
    public RankImitator(RankedCard model) {
        this(model.getRank());
    }

    /**
     * @param rank Rank to imitate.
     */
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
