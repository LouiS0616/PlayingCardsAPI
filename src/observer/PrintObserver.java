package observer;

import card.Card;
import cards.PlayableCards;
import cards.Observer;

public class PrintObserver implements Observer {
    public void update(Type type, Card card, PlayableCards self, PlayableCards other) {
        switch(type) {
            case ADD:  print(card, other, self); break;
            case PICK: print(card, self, other); break;
        }
    }

    private void print(Card card, PlayableCards from, PlayableCards to) {
        System.out.println(
            String.format("Pick %s from %s to %s.", card, from, to)
        );
    }
}
