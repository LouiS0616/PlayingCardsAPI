package cards;

import card.Card;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Cards implements Iterable<Card> {
    protected abstract Card pick();
    protected abstract void add(Card card);
    public void pickFrom(Cards from) {
        add(from.pick());
    }
    public void pickFrom(Cards from, int num) {
        for(int i = 0; i < num; ++i) {
            pickFrom(from);
        }
    }

    public abstract Stream<Card> stream();

    @Override
    public String toString() {
        return this.stream()
            .map(Card::toString)
            .collect(Collectors.joining(", "))
        ;
    }
}
