package card.imitator.wild;

import card.Card;
import card.imitator.CardImitator;

import java.util.Arrays;
import java.util.List;

public class UnitedImitator implements WildCardImitator {
    public enum OperationType { AND, OR }

    // Be careful not to occur Loop Reference.
    public UnitedImitator(OperationType type, CardImitator... imitators) {
        this.type_ = type;
        this.imitators_ = Arrays.asList(imitators);
    }

    @Override
    public boolean isEquivalent(Card card) {
        switch(type_) {
            case AND: return isEquivalent$and(card);
            case OR:  return isEquivalent$or (card);
        }

        throw new RuntimeException("dead code");
    }
    private boolean isEquivalent$and(Card card) {
        return imitators_.stream()
            .allMatch(imitator -> imitator.isEquivalent(card))
        ;
    }
    private boolean isEquivalent$or(Card card) {
        return imitators_.stream()
            .anyMatch(imitator -> imitator.isEquivalent(card))
        ;
    }

    //
    //
    private final OperationType type_;
    private List<CardImitator> imitators_;
}
