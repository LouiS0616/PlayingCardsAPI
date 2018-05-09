package cards;

import card.Card;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Cards implements Iterable<Card> {
    //
    private final String name_;
    private Observer observer_;

    //
    protected Cards(String name, Observer observer) {
        this.name_ = name;
        this.observer_ = observer;
    }

    //
    public class CardNotEnoughException extends RuntimeException {
    }

    protected abstract Card pick();
    protected abstract void add(Card card);
    public void pickFrom(Cards from) {
        Card card = from.pick();
        this.add(card);

        from.update(Observer.Type.PICK, card, this);
        this.update(Observer.Type.ADD,  card, from);
    }
    public void pickFrom(Cards from, int num) {
        if(from.countCard() < num) {
            throw new CardNotEnoughException();
        }

        for(int i = 0; i < num; ++i) {
            pickFrom(from);
        }
    }

    public abstract int countCard();

    protected void update(Observer.Type type, Card card, Cards other) {
        observer_.update(type, card, this, other);
    }

    public abstract Stream<Card> stream();

    @Override
    public String toString() {
        return this.name_;
    }
    public void printCards() {
        System.out.println(
            this.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "))
        );
    }
}
