package cards;

import card.Card;
import card.Suit;
import card.imitator.CardImitator;
import card.imitator.IndividualCardImitator;
import card.imitator.SuitImitator;
import card.imitator.WildCardImitator;
import exceptions.CardNotEnoughException;
import exceptions.CardNotFoundException;
import exceptions.CardAffiliationImproperException;
import util.PyLikePrinter;
import util.StreamAble;

import java.util.stream.Collectors;


public abstract class BaseCards implements Iterable<Card>, StreamAble<Card> {
    //
    // Generate methods
    BaseCards(String name) {
        this.name_ = name;
    }

    /**
     * @exception CardAffiliationImproperException When owner is tried to overwrite.
     */
    protected void setOwner(CardOwner owner) {
        if(this.affiliation_ != null) {
            throw new CardAffiliationImproperException("You can NOT reset card owner.");
        }

        this.affiliation_ = owner.getAffiliation();
    }


    //
    // Check methods
    public boolean include(CardImitator imitator) {
        return stream()
            .anyMatch(imitator::isEquivalent)
        ;
    }
    public int countCard(WildCardImitator imitator) {
        return (int)stream()
            .filter(imitator::isEquivalent)
            .count()
        ;
    }
    public abstract int countCard();


    //
    // Methods related drawing
    protected abstract void add(Card card);

    /**
     * Get a card imitator what mean drawable card.
     * @throws CardNotEnoughException When cards is empty.
     */
    protected abstract IndividualCardImitator peek() throws CardNotEnoughException;

    /**
     * @throws CardNotFoundException When card is not included in cards.
     */
    protected abstract Card draw(IndividualCardImitator purpose) throws CardNotFoundException;

    /**
     * This method is called when "from -card-{@literal >} this".
     */
    protected abstract void update(Card card, BaseCards from);


    /**
     * In any way you draw card, you MUST use this method.
     * Because this method is responsible for check card affiliation.
     */
    public void pickFrom(BaseCards from, IndividualCardImitator purpose) {
        if(this.affiliation_ == null) {
            this.affiliation_ = from.affiliation_;
        }

        if(this.affiliation_.isEquivalent(from.affiliation_)) {
            Card card = from.draw(purpose);

            update(card, from);
            add(card);
        }
        else {
            throw new CardAffiliationImproperException("You MUST NOT mix distinct deck.");
        }
    }


    //
    // Display methods
    @Override
    public String toString() {
        return this.name_;
    }

    private String toStringCards() {
        return stream()
            .map(Card::toString)
            .collect(Collectors.joining(", "))
        ;
    }
    private String toStringBreakdown() {
        return Suit.stream()
            .map(
                suit -> String.format("%s: %d", suit, countCard(new SuitImitator(suit)))
            )
            .collect(Collectors.joining(", "))
        ;
    }

    /**
     * Please use it for just print debugging.
     */
    public void printInfo() {
        new PyLikePrinter("\n").print(
            "----------------------------------------------------------------",
            "Name:        " + name_,
            "Affiliation: " + affiliation_,
            "#Card:       " + countCard(),
            "Breakdown:   " + toStringBreakdown(),
            "",
            toStringCards(),
            "----------------------------------------------------------------"
        );
    }


    //
    // Fields
    private final String name_;
    private CardAffiliation affiliation_;
}
