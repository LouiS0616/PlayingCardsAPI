package card.imitator;

import card.Card;

/**
 * User should not use Card instance avoid to card duplication.
 * When you want to express card, use imitator instead of concrete Card instance.
 */
public interface CardImitator {
    /**
     * @return Whether argument card is equivalent or not.
     */
    boolean isEquivalent(Card card);
}
