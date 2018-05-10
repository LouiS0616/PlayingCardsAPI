package cards.ordered;

import card.Card;
import card.Joker;
import card.RankedCard;
import cards.Observer;

public class Deck extends OrderedCards {
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

        for(Card card: RankedCard.generate()) {
            add(card);
        }
        for(int i = 0; i < 2; ++i) {
            add(new Joker());
        }
    }
}
