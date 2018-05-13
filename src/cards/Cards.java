package cards;

import card.Card;
import card.imitator.individual.IndividualCardImitator;
import card.imitator.wild.WildCardImitator;
import exceptions.CardNotEnoughException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Cards extends BaseCards {
    //
    // Generate methods
    protected Cards(String name, Observer observer) {
        super(name);
        this.observer_ = observer;
    }

    //
    // Class specific methods
    @Override
    protected final void update(Card card, BaseCards from) {
        observer_.update(
            Observer.Type.ADD, card, this, from
        );

        if(from instanceof Cards) {
            Cards from_ = (Cards)from;
            from_.observer_.update(
                Observer.Type.PICK, card, from_, this
            );
        }
    }


    //
    // Methods related drawing

    //
    // Randomly drawing
    public void pickFrom(Cards from, int num) throws CardNotEnoughException {
        if(from.countCard() < num) {
            throw new CardNotEnoughException();
        }

        for(int i = 0; i < num; ++i) {
            IndividualCardImitator imitator = from.peek();
            pickFrom(from, imitator);
        }
    }


    //
    // Specific drawing
    public void divideFrom(Cards from, WildCardImitator wildPurpose) {
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
