package card;

import card.imitator.JokerImitator;
import cards.CardAffiliation;

import java.util.stream.Stream;

public class Joker extends Card {
    private static int serialId_ = 0;
    private final int id_;

    private Joker(CardAffiliation owner) {
        super(owner);
        this.id_ = ++serialId_;
    }
    static Stream<Card> generate$for_makeCards(CardAffiliation owner) {
        return Stream.of(
            new Joker(owner), new Joker(owner)
        );
    }

    @Override
    public JokerImitator getIndividualImitator() {
        return new JokerImitator();
    }

    //
    // Basically methods
    @Override
    public String toString() {
        return "Joker";
    }

    // This method should be regarded as semi-private.
    public static int compareId$for_comparator(Joker joker1, Joker joker2) {
        return Integer.compare(joker1.id_, joker2.id_);
    }
}
