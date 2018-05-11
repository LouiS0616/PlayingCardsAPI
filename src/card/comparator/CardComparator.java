package card.comparator;

import card.Card;
import card.Joker;

import java.util.Comparator;

public abstract class CardComparator implements Comparator<Card> {
    //
    // Comparison methods
    protected final int compare(Joker joker1, Joker joker2) {
        return Joker.compareId$for_comparator(joker1, joker2);
    }

    //
    // Basically methods
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
