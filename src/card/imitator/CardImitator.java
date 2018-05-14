package card.imitator;

import card.Card;

/**
 * It imitates a card that class implements this interface.
 * User should not use Card instance avoid to card duplication.
 * When you want to express card, use imitator proactively instead of concrete Card instance.
 */
public interface CardImitator {
    /**
     * @param card The card judged equivalent to this.
     * @return Whether card is equivalent or not.
     */
    boolean isEquivalent(Card card);
}
