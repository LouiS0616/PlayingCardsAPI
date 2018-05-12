package cards.own;

import card.Card;

public class CardAffiliation {
    public boolean own(Card card) {
        return card != null && card.isRegisteredAt(this);
    }
}
