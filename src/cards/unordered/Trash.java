package cards.unordered;

import card.Card;
import card.imitator.individual.IndividualCardImitator;
import cards.Observer;

import java.util.Iterator;
import java.util.stream.Stream;

public final class Trash extends UnorderedCards {
    //
    // Generate methods
    public static Trash makeTrash() {
        return makeTrash(Observer.STUB);
    }
    public static Trash makeTrash(Observer observer) {
        if(instance_ != null) {
            return instance_;
        }

        return instance_ = new Trash(observer);
    }
    private static Trash instance_ = null;

    private Trash(Observer observer) {
        super("Trash", observer);
    }

    //
    // Prohibited operations
    public class ProhibitedOperationException extends RuntimeException {
        private ProhibitedOperationException(String message) {
            super(
                String.format("The %s operation to trash is prohibited.", message)
            );
        }
    }

    @Override
    public Iterator<Card> iterator() {
        throw new ProhibitedOperationException("getting iterate");
    }
    @Override
    public Stream<Card> stream() {
        throw new ProhibitedOperationException("getting stream");
    }

    @Override
    protected Card pick() {
        throw new ProhibitedOperationException("picking a card");
    }
    @Override
    protected Card pick(IndividualCardImitator purpose) {
        throw new ProhibitedOperationException("picking a card");
    }
}
