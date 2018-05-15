package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.imitator.IndividualCardImitator;
import nsr.loui.playingcards.exceptions.CardNotEnoughException;
import nsr.loui.playingcards.exceptions.CardNotFoundException;
import nsr.loui.playingcards.exceptions.ProhibitedOperationException;
import nsr.loui.playingcards.observer.Observer;
import nsr.loui.util.CollectionUtil;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * This class treats ORDERED cards.
 * You can draw top card of cards, but cannot draw card randomly.
 * This class has cards as LinkedList for what reason its performance.
 */
public abstract class OrderedCards extends Cards {
    //
    // Generate methods

    /**
     * @param name name of cards used for print info.
     * @param observer observer. DON'T pass null, DO use stub instead.
     */
    protected OrderedCards(String name, Observer observer) {
        super(name, observer);
    }

    /**
     * Set cards and these owner when THIS have empty cards.
     * @param cards what you want THIS to hold.
     * @param owner valid card owner.
     * @exception ProhibitedOperationException when originally cards is not empty.
     */
    void setCards(LinkedList<Card> cards, CardOwner owner) {
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

    /**
     * This method implemented just by calling java.util.Collections.shuffle .
     */
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
    final Stream<Card> stream() {
        return cards_.stream();
    }

    //
    // Methods related drawing

    /**
     * @return imitator what behave as the top card.
     */
    @Override
    public final IndividualCardImitator peek() {
        if(countCard() == 0) {
            throw new CardNotEnoughException();
        }

        return cards_.get(0).getIndividualImitator();
    }
    @Override
    final Card draw(IndividualCardImitator purpose) {
        return CollectionUtil.popElem(
            cards_,
            stream()
                .filter(purpose::isEquivalent)
                .findFirst()
                .orElseThrow(CardNotFoundException::new)
        );
    }
    @Override
    final void add(Card card) {
        cards_.add(card);
    }

    //
    // Fields

    // This field should be LinkedList object instead of List.
    // Because OrderedCards does'nt allow random access.
    private LinkedList<Card> cards_ = new LinkedList<>();
}
