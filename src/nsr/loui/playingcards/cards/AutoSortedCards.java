package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.card.imitator.IndividualCardImitator;
import nsr.loui.playingcards.exceptions.CardNotEnoughException;
import nsr.loui.playingcards.observer.Observer;
import nsr.loui.util.CollectionUtil;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * You can draw a card randomly, but you cannot draw top card of cards.
 * Auto-sorting concept is close to unordered rather than ordered that, so their implementation are similar.
 *
 * This class has cards as SortedSet.
 */
public class AutoSortedCards extends Cards {
    //
    // Generate methods

    /**
     * @param name name of cards used for print info.
     * @param observer observer. DON'T pass null, DO use stub instead.
     * @param comparator comparator used for auto-sorting.
     */
    protected AutoSortedCards(String name, Observer observer, CardComparator comparator) {
        super(name, observer);
        this.sortedCardSet_ = new TreeSet<>(comparator);
    }


    //
    // Class-specific methods

    /**
     * Behaves such as re-sorting.
     * This method make new instance internally, so pay attention to its computational complexity.
     * @param comparator new comparator to set.
     */
    protected final void resetComparator(CardComparator comparator) {
        if(comparator.equals(this.sortedCardSet_.comparator())) {
            return;
        }

        SortedSet<Card> tmp = new TreeSet<>(comparator);
        tmp.addAll(this.sortedCardSet_);
        this.sortedCardSet_ = tmp;
    }


    //
    // Check methods
    @Override
    public final int countCard() {
        return sortedCardSet_.size();
    }


    //
    // Iterate methods
    @Override
    final Stream<Card> stream() {
        return sortedCardSet_.stream();
    }


    //
    // Methods related drawing

    /**
     * @return imitator what behave as card chosen randomly.
     */
    @Override
    public final IndividualCardImitator peek() {
        if(countCard() == 0) {
            throw new CardNotEnoughException();
        }

        Card card = (Card)sortedCardSet_.toArray()[randIndex()];
        return card.getIndividualImitator();
    }
    @Override
    final Card draw(IndividualCardImitator purpose) {
        return CollectionUtil.popElem(
            sortedCardSet_,
            stream()
                .filter(purpose::isEquivalent)
                .findFirst()
                .orElseThrow(CardNotEnoughException::new)
        );
    }
    @Override
    final void add(Card card) {
        if(!sortedCardSet_.add(card)) {
            System.err.println("You may use duplicated cards.");
        }
    }


    //
    // Fields and utility
    private SortedSet<Card> sortedCardSet_;
    private final Random rand_ = new Random();

    private int randIndex() {
        return rand_.nextInt(sortedCardSet_.size());
    }
}
