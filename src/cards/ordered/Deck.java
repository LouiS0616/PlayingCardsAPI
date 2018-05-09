package cards.ordered;

import card.Card;
import card.Joker;
import card.RankedCard;
import cards.Observer;

public class Deck extends OrderedCards {
    public Deck(Observer observer) {
        this(2, observer);
    }
    public Deck(int numOfJoker) {
        this(numOfJoker, Observer.STUB);
    }
    public Deck(int numOfJoker, Observer observer) {
        super("Deck", observer);

        for(Card card: RankedCard.generate()) {
            add(card);
        }
        for(int i = 0; i < numOfJoker; ++i) {
            add(new Joker());
        }
    }
}
