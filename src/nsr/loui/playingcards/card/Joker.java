package nsr.loui.playingcards.card;

import nsr.loui.playingcards.card.imitator.JokerImitator;
import nsr.loui.playingcards.cards.CardOwnerCertificate;

import java.util.stream.Stream;

/**
 * This class means a joker.
 * Though jokers are shown as same by user, it should be distinguishable for hash container.
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

    /**
     * @return "Joker"
     */
    @Override
    public String toString() {
        return "Joker";
    }


    /**
     * This method should be regarded as semi-private.
     * User does not have to pay attention to its implementation.
     * @param joker1 a joker.
     * @param joker2 a joker.
     * @return Integer.compare(joker1.id_, joker2.id_)
     */
    public static int compareId$for_comparator(Joker joker1, Joker joker2) {
        return Integer.compare(joker1.id_, joker2.id_);
    }
}
