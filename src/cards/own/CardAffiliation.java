package cards.own;

import card.Card;

public class CardAffiliation {
    public CardAffiliation(String name) {
        this.name_ = name;
    }
    public boolean own(Card card) {
        return card != null && card.isRegisteredAt(this);
    }

    @Override
    public String toString() {
        return this.name_;
    }

    //
    //
    private final String name_;
}
