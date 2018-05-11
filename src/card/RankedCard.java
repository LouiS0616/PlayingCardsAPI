package card;

import card.imitator.individual.RankedCardImitator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RankedCard extends Card {
    //
    // Generate methods
    private RankedCard(Suit suit, int rank) {
        this.suit_ = suit;
        this.rank_ = rank;
    }
    public static List<Card> generate() {
        if(defaultCardList_ != null) {
            return defaultCardList_;
        }

        return defaultCardList_ = IntStream.rangeClosed(1, 13)
            .boxed()
            .flatMap(
                i -> Suit.stream().map(
                    s -> new RankedCard(s, i)
                )
            )
            .collect(Collectors.toList())
        ;
    }
    private static List<Card> defaultCardList_ = null;

    @Override
    public RankedCardImitator getImitator() {
        return new RankedCardImitator(
            this.suit_, this.rank_
        );
    }

    //
    // Basically methods
    @Override
    public String toString() {
        return String.format(
            "%s%s",
            suit_, rankToMark_.getOrDefault(rank_, String.valueOf(rank_))
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
    private Suit suit_;
    private int rank_;

    public Suit getSuit() {
        return suit_;
    }
    public int getRank() {
        return rank_;
    }
}
