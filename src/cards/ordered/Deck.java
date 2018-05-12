package cards.ordered;

import card.Card;
import cards.CardOwner;
import cards.Observer;

public final class Deck extends OrderedCards {
    //
    // Generate methods
    private static Deck instance_ = null;
    public static Deck makeDeck() {
        return makeDeck(Observer.STUB);
    }
    public static Deck makeDeck(Observer observer) {
        if(instance_ != null) {
            return instance_;
        }

        return instance_ = new Deck(observer);
    }

    private Deck(Observer observer) {
        super("Deck", observer, Card.makeCards$for_deck());
        //setOwner(owner_ = new CardOwner());
    }

    //private final CardOwner owner_;
}
