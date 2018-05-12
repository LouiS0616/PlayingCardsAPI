package cards;

import card.Card;
import card.imitator.CardImitator;
import card.imitator.individual.IndividualCardImitator;
import card.imitator.wild.WildCardImitator;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Cards extends BaseCards {
    //
    // Generate methods
    protected Cards(String name, Observer observer) {
        super(name);
        this.observer_ = observer;
    }

    //
    // Methods related drawing
    protected abstract void add(Card card);
    private void update(Observer.Type type, Card card, Cards other) {
        observer_.update(type, card, this, other);
    }

    //
    // Randomly drawing
    public class CardNotEnoughException extends RuntimeException { }

    protected abstract IndividualCardImitator pickImitator();

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
            pickFrom(from, from.pickImitator());
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

        purposes.forEach(imitator -> pickFrom(from, imitator));
    }
    public void divideFrom(Cards from, WildCardImitator... wildPurposes) {
        for(WildCardImitator wildPurpose: wildPurposes) {
            divideFrom(from, wildPurpose);
        }
    }

    //
    //
    private final Observer observer_;
}
