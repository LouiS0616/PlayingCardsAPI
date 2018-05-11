package cards.ordered;

import card.Card;
import card.imitator.individual.IndividualCardImitator;
import cards.Cards;
import cards.Observer;
import util.CollectionUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public abstract class OrderedCards extends Cards {
    //
    // Generate methods
    protected OrderedCards(String name, Observer observer) {
        super(name, observer);
    }

    //
    // Class-specific methods
    public final void shuffle() {
        Collections.shuffle(this.cards_);
    }

    //
    // Check methods
    @Override
    public final int countCard() {
        return cards_.size();
    }

    //
    // Iterate methods
    @Override
    public final Iterator<Card> iterator() {
        return cards_.iterator();
    }
    @Override
    public final Stream<Card> stream() {
        return cards_.stream();
    }

    //
    // Methods related drawing
    @Override
    protected final Card pick() {
        return cards_.remove(0);
    }
    @Override
    protected final Card pick(IndividualCardImitator purpose) throws CardNotFoundException {
        return CollectionUtil.popElem(
            cards_,
            stream()
                .filter(purpose::isEquivalent)
                .findFirst()
                .orElseThrow(CardNotFoundException::new)
        );
    }
    @Override
    protected final void add(Card card) {
        cards_.add(card);
    }

    //
    // Fields
    private final List<Card> cards_ = new LinkedList<>();
}
