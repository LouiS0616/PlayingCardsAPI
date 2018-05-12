package cards.ordered;

import card.Card;
import cards.CardOwner;
import cards.Observer;

public final class Deck extends OrderedCards {
    //
    // Generate methods
    public Deck() {
        this(Observer.STUB);
    }
    public Deck(Observer observer) {
        super("Deck", observer);
        setCards(
            Card.makeCards$for_deck(this.owner_ = new CardOwner())
        );
    }

    private final CardOwner owner_;
}
