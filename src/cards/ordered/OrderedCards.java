package cards.ordered;

import card.Card;
import cards.Cards;
import cards.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class OrderedCards extends Cards {
    public OrderedCards(String name) {
        this(name, Observer.STUB);
    }
    public OrderedCards(String name, Observer observer) {
        super(name, observer);
    }

    //
    public void shuffle() {
        Collections.shuffle(this.cards_);
    }

    //
    @Override
    protected final Card pick() {
        return cards_.remove(0);
    }
    @Override
    protected final void add(Card card) {
        cards_.add(card);
    }

    //
    @Override
    public final Stream<Card> stream() {
        return cards_.stream();
    }
    @Override
    public Iterator<Card> iterator() {
        return cards_.iterator();
    }

    //
    //
    private final List<Card> cards_ = new ArrayList<>();
}
