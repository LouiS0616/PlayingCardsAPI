package card.comparator;

import card.RankedCard;

import java.util.Comparator;

/**
 * This class provide natural card comparison.
 * Cards aligned such as [Spade A, Heart A, Spade 3, ..., Joker].
 */
public class DefaultCardComparator extends JokersLastComparator {
    @Override
    protected int compare(RankedCard rankedCard1, RankedCard rankedCard2) {
        return Comparator
            .comparingInt(RankedCard::getRank)
            .thenComparing(RankedCard::getSuit)
            .compare(rankedCard1, rankedCard2)
        ;
    }
}
