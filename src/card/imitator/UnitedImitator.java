package card.imitator;

import card.Card;

import java.util.Arrays;
import java.util.List;

/**
 * Class what can concatenate imitators by OR condition.
 */
public class UnitedImitator implements WildCardImitator {
    /**
     * Be careful not to occur Loop Reference.
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
    private List<CardImitator> imitators_;
}
