package cards.ordered;

import card.Card;
import card.Joker;
import card.RankedCard;

public class Deck extends OrderedCards {
    public Deck() {
        this(2);
    }
    public Deck(int numOfJoker) {
        for(Card card: RankedCard.generate()) {
            add(card);
        }
        for(int i = 0; i < numOfJoker; ++i) {
            add(new Joker());
        }
    }
}
