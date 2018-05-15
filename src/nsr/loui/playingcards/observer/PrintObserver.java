package nsr.loui.playingcards.observer;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.cards.BaseCards;
import nsr.loui.playingcards.cards.Cards;

/**
 * This class report transfer on stdout just for debug, or just be implementation sample.
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
