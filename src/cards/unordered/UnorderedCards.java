package cards.unordered;

import card.Card;
import cards.Cards;
import cards.Observer;
import util.CollectionUtil;

import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

public class UnorderedCards extends Cards {
    public UnorderedCards(String name) {
        this(name, Observer.STUB);
    }
    public UnorderedCards(String name, Observer observer) {
        super(name, observer);
    }

    @Override
    public Card pick() {
        return CollectionUtil.popElem(
            cardSet_, (Card)cardSet_.toArray()[randIndex()]
        );
    }
    @Override
    public void add(Card card) {
        if(!cardSet_.add(card)) {
            System.err.println("You may use deprecated cards.");
        }
    }

    @Override
    public Iterator<Card> iterator() {
        return cardSet_.iterator();
    }
    @Override
    public Stream<Card> stream() {
        return cardSet_.stream();
    }

    //
    //
    private final SortedSet<Card> cardSet_ = new TreeSet<>();
    private final Random rand_ = new Random();

    private int randIndex() {
        return rand_.nextInt(cardSet_.size());
    }
}
