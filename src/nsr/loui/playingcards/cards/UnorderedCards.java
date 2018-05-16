package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.imitator.IndividualCardImitator;
import nsr.loui.playingcards.exceptions.CardNotEnoughException;
import nsr.loui.playingcards.exceptions.CardNotFoundException;
import nsr.loui.playingcards.exceptions.ProhibitedOperationException;
import nsr.loui.playingcards.observer.Observer;
import nsr.loui.util.CollectionUtil;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This class treats UNORDERED cards.
 * You can draw a card randomly, but you cannot draw top card of cards.
 *
 * This class has cards as Set.
 */
public class UnorderedCards extends Cards {
    //
    // Generate methods

    /**
     * @param name name of cards used for print info.
     * @param observer observer. DON'T pass null, DO use stub instead.
     */
    protected UnorderedCards(String name, Observer observer) {
        this(name, observer, new HashSet<>());
    }

    /**
     * @param name name of cards used for print info.
     * @param observer observer. DON'T pass null, DO use stub instead.
     * @param cardSet card set haven internally.
     */
    protected UnorderedCards(String name, Observer observer, Set<Card> cardSet) {
        super(name, observer);
        this.cardSet_ = cardSet;
    }

    /**
     * Set cards and these owner when THIS have empty cards.
     * @param cards what you want THIS to hold.
     * @param owner valid card owner-certificate.
     * @throws ProhibitedOperationException when originally cards is not empty.
     */
    void setCards(Set<Card> cards, CardOwnerCertificate certificate) {
        if(this.cardSet_.isEmpty()) {
            this.cardSet_ = cards;
            setCertificate(certificate);
        }
        else {
            throw new ProhibitedOperationException();
        }
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
    Stream<Card> stream() {
        return cardSet_.stream();
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

        Card card = (Card)cardSet_.toArray()[randIndex()];
        return card.getIndividualImitator();
    }
    @Override
    final Card draw(IndividualCardImitator purpose) {
        return CollectionUtil.popElem(
            cardSet_,
            stream()
                .filter(purpose::isEquivalent)
                .findFirst()
                .orElseThrow(CardNotFoundException::new)
        );
    }
    @Override
    final void add(Card card) {
        if(!cardSet_.add(card)) {
            System.err.println("You may use deprecated cards.");
        }
    }

    //
    // Fields and utility
    private Set<Card> cardSet_;
    private final Random rand_ = new Random();

    private int randIndex() {
        return rand_.nextInt(cardSet_.size());
    }
}
