package cards;

import card.Card;
import card.Suit;
import card.imitator.CardImitator;
import card.imitator.individual.IndividualCardImitator;
import card.imitator.wild.SuitImitator;
import card.imitator.wild.WildCardImitator;
import exceptions.CardNotEnoughException;
import exceptions.CardNotFoundException;
import exceptions.CardOwnerImproperException;
import cards.own.CardAffiliation;
import cards.own.CardOwner;
import util.PyLikePrinter;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class BaseCards implements Iterable<Card> {
    //
    // Generate methods
    BaseCards(String name) {
        this.name_ = name;
    }
    public void setOwner(CardOwner owner) {
        if(this.affiliation_ != null) {
            throw new CardOwnerImproperException("You can NOT reset card owner.");
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
    protected abstract void add$owner_is_already_checked(Card card, Cards from);

    final void add$any_check_is_undone(Card card, Cards from) throws CardOwnerImproperException {
        if(affiliation_ == null) {
            throw new CardOwnerImproperException("You MUST register cards to valid card-owner.");
        }
        if(!affiliation_.own(card)) {
            throw new CardOwnerImproperException("You MUST NOT mix distinct decks.");
        }

        add$owner_is_already_checked(card, from);
    }

    protected abstract IndividualCardImitator peek()             throws CardNotEnoughException;
    protected abstract Card draw(IndividualCardImitator purpose) throws CardNotFoundException;


    //
    // Iterate methods
    public abstract Iterator<Card> iterator();
    public abstract Stream<Card> stream();


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
