package card;

import card.imitator.individual.IndividualCardImitator;
import cards.CardOwner;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Card {
    Card(CardOwner owner) {
        this.owner_ = owner;
    }

    public static LinkedList<Card> makeCards$for_deck(CardOwner owner) {
        return Stream.concat(
                RankedCard.generate$for_makeCards(owner),
                Joker     .generate$for_makeCards(owner)
            )
            .collect(Collectors.toCollection(LinkedList::new))
        ;
    }

    //
    public abstract IndividualCardImitator getIndividualImitator();

    //
    private final CardOwner owner_;
    public boolean isRegisteredAt(CardOwner owner) {
        return this.owner_ == owner;
    }
}
