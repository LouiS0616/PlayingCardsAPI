package nsr.loui.playingcards.card.imitator;

import nsr.loui.playingcards.card.Card;

import java.util.Arrays;
import java.util.List;

/**
 * This class can concatenate imitators by OR condition.
 */
public class UnitedImitator implements WildCardImitator {
    /**
     * Be careful not to occur Loop Reference.
     * @param imitators Imitators to concatenate.
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
