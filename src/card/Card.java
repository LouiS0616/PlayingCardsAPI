package card;

import card.imitator.IndividualCardImitator;
import cards.CardOwnerCertificate;
import exceptions.OwnerCertificateImproperException;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class means a playing card abstractly.
 * This should be extended only by Joker and RankedCard class.
 */
public abstract class Card {
    Card(CardOwnerCertificate certificate) {
        this.cardOwnerCertificate_ = certificate;
    }

    /**
     * This method should be called by only Deck class avoid to card duplication.
     * @param certificate valid card certificate.
     * @return a set of playing cards, including jokers.
     * @throws OwnerCertificateImproperException when CERTIFICATE is null.
     */
    public static LinkedList<Card> makeCards$for_deck(CardOwnerCertificate certificate) {
        if(certificate == null) {
            throw new OwnerCertificateImproperException("You MUST use valid certificate.");
        }

        return Stream.concat(
                RankedCard.generate$for_makeCards(certificate),
                Joker     .generate$for_makeCards(certificate)
            )
            .collect(Collectors.toCollection(LinkedList::new))
        ;
    }

    /**
     * @return its imitator.
     */
    public abstract IndividualCardImitator getIndividualImitator();


    //
    //
    private final CardOwnerCertificate cardOwnerCertificate_;

    /**
     * Card should be have its certificate for future extension.
     * @return its certificate.
     */
    @SuppressWarnings("unused")
    public CardOwnerCertificate getCardCertificate() {
        return cardOwnerCertificate_;
    }
}
