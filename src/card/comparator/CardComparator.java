package card.comparator;

import card.Card;
import card.Joker;

import java.util.Comparator;

public abstract class CardComparator implements Comparator<Card> {
    protected final int compare(Joker joker1, Joker joker2) {
        return Joker.compareId(joker1, joker2);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
