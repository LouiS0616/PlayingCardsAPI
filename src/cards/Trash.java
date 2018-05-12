package cards;

import card.Card;
import card.imitator.individual.IndividualCardImitator;
import cards.exceptions.ProhibitedOperationException;
import cards.own.CardAffiliation;
import cards.own.CardOwner;

import java.util.Iterator;
import java.util.stream.Stream;

public final class Trash extends Cards implements CardOwner {
    //
    // Generate methods
    public static Trash makeTrash() {
        if(instance_ != null) {
            return instance_;
        }

        return instance_ = new Trash();
    }
    private static Trash instance_ = null;

    private Trash() {
        super("Trash", Observer.STUB);
        setOwner(this);
    }

    //
    // Class-specific methods
    private CardAffiliation affiliation_ = new CardAffiliation() {
        @Override
        public boolean own(Card card) {
            return true;
        }
    };
    @Override
    public CardAffiliation getAffiliation() {
        return affiliation_;
    }

    @Override
    public void add$all_check_has_done(Card card) {
        // Do nothing, just trash the card.
    }

    //
    // Prohibited operations
    @Override
    public int countCard() {
        throw new ProhibitedOperationException();
    }

    @Override
    public Iterator<Card> iterator() {
        throw new ProhibitedOperationException();
    }
    @Override
    public Stream<Card> stream() {
        throw new ProhibitedOperationException();
    }

    @Override
    protected Card draw() {
        throw new ProhibitedOperationException();
    }
    @Override
    protected Card draw(IndividualCardImitator purpose) {
        throw new ProhibitedOperationException();
    }
}
