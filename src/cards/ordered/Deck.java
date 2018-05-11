package cards.ordered;

import card.Card;
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
        super("Deck", observer);

        for(Card card: Card.makeCards$for_deck()) {
            add(card);
        }
    }
}
