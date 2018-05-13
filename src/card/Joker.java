package card;

import card.imitator.JokerImitator;
import cards.CardAffiliation;

import java.util.stream.Stream;

/**
 * Though jokers are shown as same by user, it should be distinguishable.
 * Because this might be contained by hash map.
 */
public final class Joker extends Card {
    private static int serialId_ = 0;
    private final int id_;

    //
    // Generate methods
    private Joker(CardAffiliation owner) {
        super(owner);
        this.id_ = ++serialId_;
    }
    static Stream<Card> generate$for_makeCards(CardAffiliation affiliation) {
        return Stream.of(
            new Joker(affiliation), new Joker(affiliation)
        );
    }

    //
    //
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


    /**
     * This method should be regarded as semi-private.
     * @return Integer.compare(joker1.id_, joker2.id_)
     */
    public static int compareId$for_comparator(Joker joker1, Joker joker2) {
        return Integer.compare(joker1.id_, joker2.id_);
    }
}
