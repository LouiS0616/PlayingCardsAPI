package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.imitator.IndividualCardImitator;
import nsr.loui.playingcards.card.imitator.WildCardImitator;
import nsr.loui.playingcards.exceptions.CardNotEnoughException;
import nsr.loui.playingcards.observer.Observer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * High-functioning than BaseCards, however this does NOT hold cards concretely yet.
 * Users except core-developer had better not extend this class directory after all.
 *
 * This class has responsibility for observe cards' transfer.
 */
public abstract class Cards extends BaseCards {
    //
    // Generate methods

    /**
     * If you don't observe cards, pass Observer.STUB as arg OBSERVER.
     * @param name name of cards used for print info.
     * @param observer observer. DON'T pass null, DO use stub instead.
     */
    Cards(String name, Observer observer) {
        super(name);
        this.observer_ = observer;
    }

    //
    // Class specific methods
    @Override
    protected void update(Card card, BaseCards from) {
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

    /**
     * Try to pick NUM cards from FROM to THIS.
     * This method call BaseCards#pickFrom(BaseCards, IndividualCardImitator) internally.
     * @param from where you want to pick card from.
     * @param num how many you want to pick.
     * @throws CardNotEnoughException when from.countCard() {@literal <} num.
     */
    public final void pickFrom(Cards from, int num) throws CardNotEnoughException {
        if(from.countCard() < num) {
            throw new CardNotEnoughException();
        }

        for(int i = 0; i < num; ++i) {
            IndividualCardImitator imitator = from.peek();
            pickFrom(from, imitator);
        }
    }

    /**
     * Draw cards from FROM what match WILD-PURPOSE condition.
     * This method call BaseCards#pickFrom(BaseCards, IndividualCardImitator) internally.
     * @param from where you want to divide from.
     * @param wildPurpose imitator indicate cards what you want to divide.
     */
    public final void divideFrom(Cards from, WildCardImitator wildPurpose) {
        // DO make list first avoid to java.util.ConcurrentModificationException.
        List<IndividualCardImitator> purposes = from.stream()
            .filter(wildPurpose::isEquivalent)
            .map(Card::getIndividualImitator)
            .collect(Collectors.toList())
        ;

        purposes.forEach(imitator -> pickFrom(from, imitator));
    }

    /**
     * This method call divideFrom(WildCardImitator) for each WILD-PURPOSE.
     * @param from where you want to divide from.
     * @param wildPurposes imitators indicate cards what you want to divide.
     */
    public final void divideFrom(Cards from, WildCardImitator... wildPurposes) {
        for(WildCardImitator wildPurpose: wildPurposes) {
            divideFrom(from, wildPurpose);
        }
    }

    //
    //
    private final Observer observer_;
}
