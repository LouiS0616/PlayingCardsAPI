package cards;

import card.Card;

@FunctionalInterface
public interface Observer {
    enum Type {
        ADD, PICK,
    }

    /**
     * @param other The other cards where card is from or card is to.
     */
    void update(Type type, Card card, PlayableCards self, PlayableCards other);

    Observer STUB =
        (Type type, Card card, PlayableCards self, PlayableCards other) -> {
            // Do nothing.
        }
    ;
}
