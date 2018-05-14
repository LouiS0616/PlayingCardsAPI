package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.observer.Observer;

/**
 * When you construct a deck, a set of cards are set automatically.
 * These cards' owner is THIS.
 */
public final class Deck extends OrderedCards implements CardOwner {
    private static int serialId_ = 0;

    //
    // Generate methods
    public Deck() {
        this(Observer.STUB);
    }
    public Deck(Observer observer) {
        super("Deck" + serialId_, observer);

        this.affiliation_ = new CardOwnerCertificate("Deck" + serialId_);
        setCards(
            Card.makeCards$for_deck(this.affiliation_), this
        );

        ++serialId_;
    }

    //
    // Affiliation
    @Override
    public CardOwnerCertificate getCertificate() {
        return this.affiliation_;
    }
    private final CardOwnerCertificate affiliation_;
}
