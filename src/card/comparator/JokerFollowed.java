package card.comparator;

import card.Card;
import card.Joker;
import card.RankedCard;

public abstract class JokerFollowed extends CardComparator {
    //
    // Comparison methods
    protected abstract int compare(RankedCard rankedCard1, RankedCard rankedCard2);

    @Override
    public final int compare(Card card1, Card card2) {
        boolean card1IsJoker = card1 instanceof Joker;
        boolean card2IsJoker = card2 instanceof Joker;

        if(card1IsJoker && card2IsJoker) {
            return compare(
                (Joker)card1, (Joker)card2
            );
        }

        if(card1IsJoker) return +1;
        if(card2IsJoker) return -1;

        return compare(
            (RankedCard)card1, (RankedCard)card2
        );
    }
}
