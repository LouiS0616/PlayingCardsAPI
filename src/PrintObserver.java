import card.Card;
import cards.Cards;
import cards.Observer;

public class PrintObserver implements Observer {
    public void update(Type type, Card card, Cards other) {
        if(type == Type.ADD) {
            System.out.println(
                String.format("Pick %s from %s.", card, other)
            );
        }
    }
}
