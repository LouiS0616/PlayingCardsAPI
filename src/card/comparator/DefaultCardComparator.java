package card.comparator;

import card.RankedCard;

import java.util.Comparator;

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
