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
    protected abstract Card pick();
    protected abstract void add(Card card);
    public void pickFrom(Cards from) {
        Card card = from.pick();
        this.add(card);

        from.observer_.update(Observer.Type.PICK, card, this);
        this.observer_.update(Observer.Type.ADD,  card, from);
    }
    public void pickFrom(Cards from, int num) {
        for(int i = 0; i < num; ++i) {
            pickFrom(from);
        }
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
