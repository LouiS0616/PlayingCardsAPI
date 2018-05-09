import card.Card;
import cards.Cards;
import cards.Observer;

public class PrintObserver implements Observer {
    public void update(Type type, Card card, Cards self, Cards other) {
        switch(type) {
            case ADD:  whenAdded (card, self, other); break;
            case PICK: whenPicked(card, self, other); break;
        }
    }

    private void whenAdded(Card card, Cards self, Cards other) {
        System.out.println(
            String.format("Pick %s from %s to %s.", card, other, self)
        );
    }
    private void whenPicked(Card card, Cards self, Cards other) {

    }
}
