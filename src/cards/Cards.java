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
    // Methods related drawing
    protected abstract void add$all_check_has_done(Card card);

    @Override
    protected final void add$owner_is_already_checked(Card card, Cards from) {
        this.observer_.update(Observer.Type.ADD, card, this, from);
        from.observer_.update(Observer.Type.PICK, card, from, this);

        add$all_check_has_done(card);
    }


    //
    // Randomly drawing
    public void pickFrom(Cards from, int num) throws CardNotEnoughException {
        if(this == from) {
            return;
        }

        if(from.countCard() < num) {
            throw new CardNotEnoughException();
        }

        for(int i = 0; i < num; ++i) {
            Card card = from.draw();
            this.add(card, from);
        }
    }

    //
    // Specific drawing
    public void pickFrom(Cards from, IndividualCardImitator purpose) {
        if(this == from) {
            return;
        }

        Card card = from.draw(purpose);
        this.add(card, from);
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
