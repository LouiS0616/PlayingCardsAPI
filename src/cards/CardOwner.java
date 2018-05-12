package cards;

import card.Card;

public class CardOwner {
    public boolean own(Card card) {
        return card != null && card.isRegisteredAt(this);
    }
}
