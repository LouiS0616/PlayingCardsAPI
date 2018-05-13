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
    void update(Type type, Card card, Cards self, BaseCards other);

    Observer STUB =
        (Type type, Card card, Cards self, BaseCards other) -> {
            // Do nothing.
        }
    ;
}
