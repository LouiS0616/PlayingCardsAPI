package loui.playingcards.card;

import loui.playingcards.card.imitator.RankedCardImitator;
import loui.playingcards.cards.CardOwnerCertificate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class means Ranked Card what has suit and rank.
 * In other words, ranked card is all cards but jokers.
 */
public final class RankedCard extends Card {
    //
    // Generate methods
    private RankedCard(Suit suit, int rank, CardOwnerCertificate owner) {
        super(owner);
        this.suit_ = suit;
        this.rank_ = rank;
    }
    static Stream<Card> generate$for_makeCards(CardOwnerCertificate owner) {
        return IntStream.rangeClosed(1, 13)
            .boxed()
            .flatMap(
                i -> Suit.stream().map(
                    s -> new RankedCard(s, i, owner)
                )
            )
        ;
    }

    //
    //
    @Override
    public RankedCardImitator getIndividualImitator() {
        return new RankedCardImitator(this);
    }

    //
    // Basically methods

    /**
     * @return like these; "[Suit:%s]A", "[Suit:%s]8" and "[Suit:%s]K".
     */
    @Override
    public String toString() {
        return String.format(
            "%s%s", suit_, rankToMark_.getOrDefault(rank_, String.valueOf(rank_))
        );
    }
    private static final Map<Integer, String> rankToMark_ = new HashMap<>();
    static {
        rankToMark_.put( 1, "A");
        rankToMark_.put(11, "J");
        rankToMark_.put(12, "Q");
        rankToMark_.put(13, "K");
    }

    //
    // Fields and Accessors
    private final Suit suit_;
    private final int rank_;

    /**
     * @return its suit.
     */
    public Suit getSuit() {
        return suit_;
    }

    /**
     * @return its rank(1~13).
     */
    public int getRank() {
        return rank_;
    }
}
