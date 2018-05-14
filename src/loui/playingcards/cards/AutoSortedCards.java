package loui.playingcards.cards;

import loui.playingcards.card.Card;
import loui.playingcards.card.comparator.CardComparator;
import loui.playingcards.card.imitator.IndividualCardImitator;
import loui.playingcards.exceptions.CardNotEnoughException;
import loui.playingcards.exceptions.CardNotFoundException;
import loui.playingcards.observer.Observer;
import loui.playingcards.util.CollectionUtil;

import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * You can draw a loui.playingcards.card randomly, but you cannot draw top loui.playingcards.card of loui.playingcards.cards.
 * Auto sorted concept is close to unordered rather than ordered that.
 *
 * This class has loui.playingcards.cards as SortedSet.
 */
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
            System.err.println("You may use deprecated loui.playingcards.cards.");
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
