package card.imitator.wild;

import card.Card;
import card.imitator.CardImitator;

import java.util.Arrays;
import java.util.List;

public class UnitedImitator implements WildCardImitator {
    // Be careful not to occur Loop Reference.
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
