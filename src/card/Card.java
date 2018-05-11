package card;

import card.imitator.individual.IndividualCardImitator;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Card {
    private static class CardDuplicationException extends RuntimeException {
    }
    public static LinkedList<Card> makeCards$for_deck() throws CardDuplicationException {
        if(cards_ != null) {
            throw new CardDuplicationException();
        }

        return cards_ =
            Stream.concat(
                RankedCard.generate$for_makeCards(), Joker.generate$for_makeCards()
            ).collect(Collectors.toCollection(LinkedList::new))
        ;
    }
    private static LinkedList<Card> cards_ = null;

    //
    public abstract IndividualCardImitator getIndividualImitator();
}
