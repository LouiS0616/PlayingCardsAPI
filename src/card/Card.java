package card;

import card.imitator.individual.IndividualCardImitator;
import cards.CardAffiliation;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Card {
    Card(CardAffiliation affiliation) {
        this.cardAffiliation_ = affiliation;
    }

    public static LinkedList<Card> makeCards$for_deck(CardAffiliation owner) {
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
    private final CardAffiliation cardAffiliation_;
}
