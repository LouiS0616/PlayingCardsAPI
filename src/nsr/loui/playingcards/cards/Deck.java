package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.observer.Observer;

/**
 * When you construct a deck, a set of cards are set automatically.
 * Deck instance can be a owner of cards.
 */
public final class Deck extends OrderedCards implements CardOwner {
    //
    // Generate methods

    /**
     * The default value is below:
     * <pre>
     *     name - "Deck".
     *     observer - Observer.STUB.
     * </pre>
     */
    public Deck() {
        this("Deck", Observer.STUB);
    }
    public Deck(String name) {
        this(name, Observer.STUB);
    }
    /**
     * @param observer observer. DON'T pass null, DO use stub instead.
     */
    public Deck(String name, Observer observer) {
        super(name, observer);

        this.certificate_ = new CardOwnerCertificate(this);
        setCards(
            Card.makeCards$for_deck(this.certificate_ ), this
        );
    }

    //
    // Certificate
    @Override
    public CardOwnerCertificate getCertificate() {
        return this.certificate_;
    }
    private final CardOwnerCertificate certificate_;
}
