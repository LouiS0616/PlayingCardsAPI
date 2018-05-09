package cards.ordered;

import card.Card;
import card.imitator.CardImitator;
import cards.Cards;
import cards.Observer;
import util.CollectionUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OrderedCards extends Cards {
    public OrderedCards(String name) {
        this(name, Observer.STUB);
    }
    public OrderedCards(String name, Observer observer) {
        super(name, observer);
    }

    //
    public final void shuffle() {
        Collections.shuffle(this.cards_);
    }

    //
    @Override
    protected final Card pick() {
        return cards_.remove(0);
    }
    @Override
    protected final Card pick(CardImitator purpose) {
        Optional<Card> ret = this.stream()
            .filter(purpose::isEquivalent)
            .findFirst()
            ;

        if(ret.isPresent()) {
            return CollectionUtil.popElem(
                this.cards_, ret.get()
            );
        }

        throw new CardNotFoundException();
    }
    @Override
    protected final void add(Card card) {
        cards_.add(card);
    }

    @Override
    public final int countCard() {
        return cards_.size();
    }

    //
    @Override
    public final Stream<Card> stream() {
        return cards_.stream();
    }
    @Override
    public final Iterator<Card> iterator() {
        return cards_.iterator();
    }

    //
    //
    private final List<Card> cards_ = new ArrayList<>();
}
