package observer;

import card.Card;
import cards.Cards;
import cards.Observer;

public class PrintObserver implements Observer {
    public void update(Type type, Card card, Cards self, Cards other) {
        switch(type) {
            case ADD:  print(card, other, self); break;
            case PICK: print(card, self, other); break;
        }
    }

    private void print(Card card, Cards from, Cards to) {
        System.out.println(
            String.format("Pick %s from %s to %s.", card, from, to)
        );
    }
}
