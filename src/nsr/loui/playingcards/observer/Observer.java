package nsr.loui.playingcards.observer;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.cards.BaseCards;
import nsr.loui.playingcards.cards.Cards;

/**
 * It can observe card transfer that classes implement this interface.
 */
@FunctionalInterface
public interface Observer {
    enum Type {
        ADD, PICK,
    }

    /**
     * This method should be called from Cards#update method.
     * When type is ADD, this method means "CARD ADDed to SELF from OTHER".
     * When type is PICK, this method means "CARD PICKed from SELF to OTHER".
     * @param type Action type for observe.
     * @param card The card transferred.
     * @param self Cards what can be the subject.
     * @param other The other cards where card is from or card is to.
     */
    void update(Type type, Card card, Cards self, BaseCards other);

    Observer STUB =
        (Type type, Card card, Cards self, BaseCards other) -> {
            // Do nothing.
        }
    ;
}
