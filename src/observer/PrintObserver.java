package observer;

import card.Card;
import cards.BaseCards;
import cards.Cards;
import cards.Observer;

public class PrintObserver implements Observer {
    public void update(Type type, Card card, Cards self, BaseCards other) {
        switch(type) {
            case ADD:  print(card, other, self); break;
            case PICK: print(card, self, other); break;
        }
    }

    private void print(Card card, BaseCards from, BaseCards to) {
        System.out.println(
            String.format("Pick %s from %s to %s.", card, from, to)
        );
    }
}
