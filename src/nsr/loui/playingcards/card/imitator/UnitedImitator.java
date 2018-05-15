package nsr.loui.playingcards.card.imitator;

import nsr.loui.playingcards.card.Card;

import java.util.Arrays;
import java.util.List;

/**
 * This class is consist of imitators concatenated on OR condition.
 */
public class UnitedImitator implements WildCardImitator {
    /**
     * Feed imitators what you want to concatenate.
     * Be careful of Loop Reference not to occur.
     * @param imitators imitators to concatenate.
     */
    public UnitedImitator(CardImitator... imitators) {
        this.imitators_ = Arrays.asList(imitators);
    }

    @Override
    public boolean isEquivalent(Card card) {
        return imitators_.stream()
            .anyMatch(imitator -> imitator.isEquivalent(card))
        ;
    }


    //
    //
    private final List<CardImitator> imitators_;
}
