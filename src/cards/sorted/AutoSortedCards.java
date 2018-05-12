package cards.sorted;

import card.Card;
import card.comparator.CardComparator;
import card.imitator.individual.IndividualCardImitator;
import cards.Cards;
import cards.Observer;
import util.CollectionUtil;

import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

public abstract class AutoSortedCards extends Cards {
    //
    // Generate methods
    protected AutoSortedCards(String name, Observer observer, CardComparator comparator) {
        super(name, observer);
        this.cardSet_ = new TreeSet<>(comparator);
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
    public final Iterator<Card> iterator() {
        return cardSet_.iterator();
    }
    @Override
    public final Stream<Card> stream() {
        return cardSet_.stream();
    }

    //
    // Methods related drawing
    @Override
    protected final IndividualCardImitator pickImitator() {
        Card card =  (Card)cardSet_.toArray()[randIndex()];
        return card.getIndividualImitator();
    }
    @Override
    protected final Card pick(IndividualCardImitator purpose) {
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
    private final SortedSet<Card> cardSet_;
    private final Random rand_ = new Random();

    private int randIndex() {
        return rand_.nextInt(cardSet_.size());
    }
}
