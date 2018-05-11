package cards.unordered;

import card.Card;
import card.imitator.individual.IndividualCardImitator;
import cards.Cards;
import cards.Observer;
import util.CollectionUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public abstract class UnorderedCards extends Cards {
    //
    // Generate methods
    protected UnorderedCards(String name, Observer observer) {
        super(name, observer);
    }

    //
    // Check methods
    @Override
    public final int countCard() {
        return cardSet_.size();
    }

    //
    // Iterate methods
    @Override
    public Iterator<Card> iterator() {
        return cardSet_.iterator();
    }
    @Override
    public Stream<Card> stream() {
        return cardSet_.stream();
    }

    //
    // Methods related drawing
    @Override
    protected Card pick() {
        return CollectionUtil.popElem(
            cardSet_, (Card)cardSet_.toArray()[randIndex()]
        );
    }
    @Override
    protected Card pick(IndividualCardImitator purpose) throws CardNotFoundException {
        return CollectionUtil.popElem(
            cardSet_,
            stream()
                .filter(purpose::isEquivalent)
                .findFirst()
                .orElseThrow(CardNotFoundException::new)
        );
    }
    @Override
    public final void add(Card card) {
        if(!cardSet_.add(card)) {
            System.err.println("You may use deprecated cards.");
        }
    }

    //
    // Fields and utility
    private final Set<Card> cardSet_ = new HashSet<>();
    private final Random rand_ = new Random();

    private int randIndex() {
        return rand_.nextInt(cardSet_.size());
    }
}
