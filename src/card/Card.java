package card;

import card.imitator.IndividualCardImitator;
import cards.CardAffiliation;
import exceptions.CardAffiliationImproperException;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class should be extended only by Joker and RankedCard class.
 */
public abstract class Card {
    Card(CardAffiliation affiliation) {
        this.cardAffiliation_ = affiliation;
    }

    /**
     * This method should be called by deck avoid to card duplication.
     * @param affiliation Valid card affiliation.
     * @return A set of playing cards, including jokers.
     */
    public static LinkedList<Card> makeCards$for_deck(CardAffiliation affiliation) {
        if(affiliation == null) {
            throw new CardAffiliationImproperException("You MUST use valid affiliation.");
        }

        return Stream.concat(
                RankedCard.generate$for_makeCards(affiliation),
                Joker     .generate$for_makeCards(affiliation)
            )
            .collect(Collectors.toCollection(LinkedList::new))
        ;
    }

    /**
     * @return Its imitator.
     */
    public abstract IndividualCardImitator getIndividualImitator();


    //
    //
    private final CardAffiliation cardAffiliation_;

    /**
     * Card should be have its affiliation for future extension.
     */
    @SuppressWarnings("unused")
    public CardAffiliation getCardAffiliation() {
        return cardAffiliation_;
    }
}
