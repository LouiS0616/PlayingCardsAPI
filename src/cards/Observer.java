package cards;

import card.Card;

public interface Observer {
    enum Type {
        ADD, PICK,
    }

    /**
     * @param other The other cards where card is from or card is to.
     */
    void update(Type type, Card card, Cards other);

    Observer STUB =
        (Type type, Card card, Cards other) -> {
            // Do nothing.
        }
    ;
}
