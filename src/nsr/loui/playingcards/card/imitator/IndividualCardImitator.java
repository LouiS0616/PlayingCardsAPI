package nsr.loui.playingcards.card.imitator;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.Joker;
import nsr.loui.playingcards.card.RankedCard;

/**
 * Classes implements this interface imitate just a card, in contrast to WildCardImitator.
 */
public interface IndividualCardImitator extends CardImitator {
    /**
     * @param model model card.
     * @return instance what can imitate MODEL.
     */
    static IndividualCardImitator make(Card model) {
        if(model instanceof RankedCard) {
            return new RankedCardImitator((RankedCard)model);
        }
        if(model instanceof Joker) {
            return new JokerImitator();
        }

        throw new RuntimeException("dead code");
    }
}
