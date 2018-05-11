package cards;

import card.Card;
import card.imitator.CardImitator;
import card.imitator.individual.IndividualCardImitator;
import card.imitator.wild.WildCardImitator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Cards implements Iterable<Card> {
    //
    // Generate methods
    protected Cards(String name, Observer observer) {
        this.name_ = name;
        this.observer_ = observer;
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
    // Iterate methods
    public abstract Stream<Card> stream();

    //
    // Methods related drawing
    protected abstract void add(Card card);
    private void update(Observer.Type type, Card card, Cards other) {
        observer_.update(type, card, this, other);
    }

    //
    // Randomly drawing
    public class CardNotEnoughException extends RuntimeException { }

    protected abstract Card pick();
    public void pickFrom(Cards from) {
        pickFrom(from, 1);
    }
    public void pickFrom(Cards from, int num) throws CardNotEnoughException {
        if(this == from) {
            return;
        }

        if(from.countCard() < num) {
            throw new CardNotEnoughException();
        }

        for(int i = 0; i < num; ++i) {
            Card card = from.pick();
            this.add(card);

            from.update(Observer.Type.PICK, card, this);
            this.update(Observer.Type.ADD,  card, from);
        }
    }

    //
    // Specific drawing
    public static class CardNotFoundException extends RuntimeException {}

    protected abstract Card pick(IndividualCardImitator purpose) throws CardNotFoundException;
    public void pickFrom(Cards from, IndividualCardImitator purpose) {
        if(this == from) {
            return;
        }

        Card card = from.pick(purpose);
        this.add(card);

        from.update(Observer.Type.PICK, card, this);
        this.update(Observer.Type.ADD,  card, from);
    }

    public void divideFrom(Cards from, WildCardImitator wildPurpose) {
        if(this == from) {
            return;
        }

        // DO make list first avoid to java.util.ConcurrentModificationException.
        List<IndividualCardImitator> purposes = from.stream()
            .filter(wildPurpose::isEquivalent)
            .map(Card::getIndividualImitator)
            .collect(Collectors.toList())
        ;

        purposes.forEach(card -> pickFrom(from, card));
    }
    public void divideFrom(Cards from, WildCardImitator... wildPurposes) {
        for(WildCardImitator wildPurpose: wildPurposes) {
            divideFrom(from, wildPurpose);
        }
    }

    //
    // Display methods
    @Override
    public String toString() {
        return this.name_;
    }
    public void printCards() {
        System.out.println(
            this.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "))
        );
    }

    //
    // Fields
    private final String name_;
    private final Observer observer_;
}
