package loui.playingcards.cards;

import loui.playingcards.card.Card;
import loui.playingcards.card.imitator.IndividualCardImitator;
import loui.playingcards.exceptions.CardNotEnoughException;
import loui.playingcards.exceptions.CardNotFoundException;
import loui.playingcards.exceptions.ProhibitedOperationException;
import loui.playingcards.observer.Observer;
import loui.playingcards.util.CollectionUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * This class treats ORDERED loui.playingcards.cards.
 * You can draw top loui.playingcards.card of loui.playingcards.cards, and cannot draw random loui.playingcards.card.
 * This class has loui.playingcards.cards as LinkedList for what reason its performance.
 */
public abstract class OrderedCards extends Cards {
    //
    // Generate methods
    protected OrderedCards(String name, Observer observer) {
        super(name, observer);
    }

    /**
     * Set loui.playingcards.cards and these owner when THIS have empty loui.playingcards.cards.
     * @param cards What you want THIS to hold.
     * @param owner Valid loui.playingcards.card owner.
     * @exception ProhibitedOperationException When originally loui.playingcards.cards is not empty.
     */
    protected void setCards(LinkedList<Card> cards, CardOwner owner) {
        if(this.cards_.isEmpty()) {
            this.cards_ = cards;
            setOwner(owner);
        }
        else {
            throw new ProhibitedOperationException();
        }
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
    protected final IndividualCardImitator peek() {
        if(countCard() == 0) {
            throw new CardNotEnoughException();
        }

        return cards_.get(0).getIndividualImitator();
    }
    @Override
    protected final Card draw(IndividualCardImitator purpose) {
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

    // This field should be LinkedList object instead of List.
    // Because OrderedCards does'nt allow random access.
    private LinkedList<Card> cards_ = new LinkedList<>();
}
