package card.comparator;

import card.Card;
import card.Joker;
import card.RankedCard;

import java.util.Comparator;

public class DefaultCardComparator extends CardComparator {
    @Override
    public int compare(Card card1, Card card2) {
        if(card1 instanceof Joker) {
            return compare((Joker)card1, card2);
        }
        if(card2 instanceof Joker) {
            return -1;
        }

        return Comparator
            .comparingInt(RankedCard::getRank)
            .thenComparing(RankedCard::getSuit)
            .compare(
                (RankedCard)card1, (RankedCard)card2
            )
        ;
    }

    private int compare(Joker joker, Card card) {
        if(card instanceof Joker) {
            return compare(joker, (Joker)card);
        }

        return 1;
    }
    private int compare(Card card, Joker joker) {
        if(card instanceof Joker){
            return compare((Joker)card, joker);
        }

        return -1;
    }
}
