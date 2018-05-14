package card;

import card.imitator.JokerImitator;
import cards.CardOwnerCertificate;

import java.util.stream.Stream;

/**
 * This class means a joker.
 * Though jokers are shown as same by user, it should be distinguishable.
 * Because these might be contained by hash map.
 */
public final class Joker extends Card {
    private static int serialId_ = 0;
    private final int id_;

    //
    // Generate methods
    private Joker(CardOwnerCertificate owner) {
        super(owner);
        this.id_ = serialId_++;
    }
    static Stream<Card> generate$for_makeCards(CardOwnerCertificate affiliation) {
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
     * User should not pay attention to its action.
     * @param joker1 A joker.
     * @param joker2 A joker.
     * @return Integer.compare(joker1.id_, joker2.id_)
     */
    public static int compareId$for_comparator(Joker joker1, Joker joker2) {
        return Integer.compare(joker1.id_, joker2.id_);
    }
}
