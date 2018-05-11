package card;

import card.imitator.individual.IndividualCardImitator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Card {
    public static class CardDuplicationException extends RuntimeException {
    }
    public static List<Card> makeCards$for_deck() {
        if(cards_ != null) {
            throw new CardDuplicationException();
        }

        return cards_ =
            Stream.concat(
                RankedCard.generate(), Joker.generate()
            ).collect(Collectors.toList())
        ;
    }
    private static List<Card> cards_ = null;

    //
    public abstract IndividualCardImitator getImitator();
}
