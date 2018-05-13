package cards;

import card.Card;

public final class Deck extends OrderedCards implements CardOwner {
    //
    // Generate methods
    public Deck() {
        this(Observer.STUB);
    }
    public Deck(Observer observer) {
        super("Deck", observer);
        setCards(
            Card.makeCards$for_deck(this.owner_ = new CardAffiliation("Deck"))
        );
        setOwner(this);
    }

    //
    // Affiliation
    @Override
    public CardAffiliation getAffiliation() {
        return this.owner_;
    }
    private final CardAffiliation owner_;
}
