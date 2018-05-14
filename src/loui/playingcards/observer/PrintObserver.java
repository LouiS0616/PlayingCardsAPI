package loui.playingcards.observer;

import loui.playingcards.card.Card;
import loui.playingcards.cards.BaseCards;
import loui.playingcards.cards.Cards;

/**
 * This class is stdout loui.playingcards.observer for just a sample or debug.
 */
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
