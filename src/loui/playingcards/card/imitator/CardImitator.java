package loui.playingcards.card.imitator;

import loui.playingcards.card.Card;

/**
 * It imitates a loui.playingcards.card that class implements this interface.
 * User should not use Card instance avoid to loui.playingcards.card duplication.
 * When you want to express loui.playingcards.card, use imitator proactively instead of concrete Card instance.
 */
public interface CardImitator {
    /**
     * @param card The loui.playingcards.card judged equivalent to this.
     * @return Whether loui.playingcards.card is equivalent or not.
     */
    boolean isEquivalent(Card card);
}
