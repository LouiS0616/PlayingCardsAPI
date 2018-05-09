package card;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    @Override
    public int compareTo(Card other) {
        if(other.isJoker()) {
            return -1;
        }

        return Comparator
            .comparingInt(RankedCard::getRank)
            .thenComparing(RankedCard::getSuit)
            .compare(this, (RankedCard)other)
        ;
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof RankedCard)) {
            return false;
        }

        RankedCard other = (RankedCard)obj;
        return other.rank_ == this.rank_
            && other.suit_ == this.suit_
        ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(
            this.rank_, this.suit_
        );
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
