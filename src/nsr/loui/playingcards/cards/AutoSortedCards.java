package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.card.imitator.IndividualCardImitator;
import nsr.loui.playingcards.exceptions.CardNotEnoughException;
import nsr.loui.playingcards.exceptions.CardNotFoundException;
import nsr.loui.playingcards.observer.Observer;
import nsr.loui.playingcards.util.CollectionUtil;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * You can draw a card randomly, but you cannot draw top card of cards.
 * Auto sorted concept is close to unordered rather than ordered that.
 *
 * This class has cards as SortedSet.
 */
public abstract class AutoSortedCards extends Cards {
    //
    // Generate methods

    /**
     * @param name name of cards used for print info.
     * @param observer observer. DON'T pass null, DO use stub instead.
     * @param comparator comparator used for auto-sort.
     */
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
    public final Stream<Card> stream() {
        return cardSet_.stream();
    }

    //
    // Methods related drawing

    /**
     * @return imitator what behave as card chosen randomly.
     */
    @Override
    protected final IndividualCardImitator peek() {
        if(countCard() == 0) {
            throw new CardNotEnoughException();
        }

        Card card = (Card)cardSet_.toArray()[randIndex()];
        return card.getIndividualImitator();
    }
    @Override
    protected final Card draw(IndividualCardImitator purpose) {
        return CollectionUtil.popElem(
            cardSet_,
            stream()
                .filter(purpose::isEquivalent)
                .findFirst()
                .orElseThrow(CardNotFoundException::new)
        );
    }
    @Override
    protected final void add(Card card) {
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
