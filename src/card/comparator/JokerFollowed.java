package card.comparator;

import card.Card;
import card.Joker;
import card.RankedCard;

import java.util.Comparator;

public abstract class JokerFollowed implements Comparator<Card> {
    //
    // Comparison methods
    public abstract int compare(RankedCard rankedCard1, RankedCard rankedCard2);

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

    protected final int compare(Joker joker1, Joker joker2) {
        return Joker.compareId(joker1, joker2);
    }

    //
    // Basically methods
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
