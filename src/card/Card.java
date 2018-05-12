package card;

import card.imitator.individual.IndividualCardImitator;
import cards.own.CardAffiliation;
import cards.exceptions.CardOwnerImproperException;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Card {
    Card(CardAffiliation owner) {
        this.owner_ = owner;
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
    private final CardAffiliation owner_;
    public boolean isRegisteredAt(CardAffiliation owner) {
        if(owner == null) {
            throw new CardOwnerImproperException("You MUST register cards to valid card-owner.");
        }
        if(this.owner_ != owner) {
            throw new CardOwnerImproperException("You MUST NOT mix distinct deck.");
        }

        return true;
    }
}
