package cards.ordered;

import card.Card;
import cards.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class OrderedCards extends Cards {
    public OrderedCards() {
        cards_ = new ArrayList<>();
    }
    public OrderedCards(List<Card> cards) {
        cards_ = new ArrayList<>(cards);
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
    private List<Card> cards_;
}
